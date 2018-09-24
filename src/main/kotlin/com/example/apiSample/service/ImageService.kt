package com.example.apiSample.service

import com.example.apiSample.mapper.ImageMapper
import com.example.apiSample.model.ImageUrl
import org.springframework.stereotype.Service
import java.awt.image.BufferedImage
import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.imageio.IIOImage
import javax.imageio.ImageIO
import javax.imageio.ImageWriteParam
import javax.imageio.ImageWriter
import javax.imageio.stream.ImageOutputStream
import java.time.LocalDateTime;


@Service
class ImageService(private val imageMapper: ImageMapper) {
    var basePath: String = "/home/ec2-user/images"

    fun getAllImageUrl(): ArrayList<ImageUrl> {
        return imageMapper.getAllImageUrl()
    }

    fun getImageUrlById(id: String): ImageUrl? {
        return imageMapper.getImageUrlById(id)
    }

    fun getImageByUrl(url: String): ByteArray {
        val path: Path = Paths.get(basePath, url)
        lateinit var byteArray: ByteArray
        try {
            byteArray = Files.readAllBytes(path)
        } catch (e: IOException) {
            e.printStackTrace()
            byteArray = Files.readAllBytes(Paths.get(basePath, "default.jpg"))
        }
        return byteArray
    }

    fun addOrModifyImage(id: String, rawData: ByteArray): Unit {
        if(getImageUrlById(id) != null) { // modify
            deleteImageFile(id)
            val fileName = addImageFile(id, rawData) // 画像の書き出し
            println("***** MODIFY IMAGE: $fileName *****")
            imageMapper.modifyImage(id, fileName) // テーブルを変更
        } else { // add
            val fileName = addImageFile(id, rawData) // 画像の書き出し
            println("***** ADD FILE: $fileName *****")
            imageMapper.addImage(id, fileName) // テーブルに記録
        }
    }

    fun addDefaultImage(id: String): Unit {
        imageMapper.addImage(id, "default.jpg") // テーブルに記録
    }

    fun deleteImage(id: String): Unit {
        deleteImageFile(id)
        imageMapper.deleteImage(id)
    }

    fun writeOutputStream(byteArray: ByteArray, outputStream: OutputStream, fileFormat: String): Unit {
        var imageInput: ByteArrayInputStream = ByteArrayInputStream(byteArray)
        var buffer: BufferedImage = ImageIO.read(imageInput)

        lateinit var writer: ImageWriter
        var iter: Iterator<ImageWriter> = ImageIO.getImageWritersByFormatName(fileFormat)
        if(iter.hasNext()) {
            writer = iter.next()
        }

        var param: ImageWriteParam = writer.defaultWriteParam
        if(param.canWriteCompressed() && fileFormat.equals("jpg")) {
            param.compressionMode = ImageWriteParam.MODE_EXPLICIT
            param.compressionQuality = 1.0f
        }

        var ios: ImageOutputStream = ImageIO.createImageOutputStream(outputStream)
        writer.output = ios
        var iioImage: IIOImage = IIOImage(buffer, null, null)
        writer.write(null, iioImage, param)
    }

    fun addImageFile(id: String, rawData: ByteArray): String {
        var fileName: String = id + "_" + LocalDateTime.now().toString() + ".jpg" // .jpg only
        var pathToFile: Path = Paths.get(basePath, fileName)
        var outputStream: OutputStream = Files.newOutputStream(pathToFile)
        writeOutputStream(rawData, outputStream, "jpg") // 書き出し
        outputStream.close()
        return fileName
    }

    fun deleteImageFile(id: String): Unit {
        val url = getImageUrlById(id)?.pathToFile
        if (!url.equals("default.jpg")) { // デフォルト画像の場合は，画像データは残しておく
            val file = File(basePath + "/" + url)
            if (file.exists()) {
                if (file.delete()) {
                    println("***** DELETED FILE: $url *****")
                } else {
                    println("***** FAILED TO DELETE FILE: $url *****")
                }
            } else {
                println("***** FILE NOT FOUND: $url *****")
            }
        }
    }
}
