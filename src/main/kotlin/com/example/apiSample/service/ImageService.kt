package com.example.apiSample.service

import com.example.apiSample.controller.GetImageUrlResponse
import com.example.apiSample.mapper.ImageMapper
import org.springframework.stereotype.Service
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.FileOutputStream
import java.io.OutputStream
import javax.imageio.IIOImage
import javax.imageio.ImageIO
import javax.imageio.ImageWriteParam
import javax.imageio.ImageWriter
import javax.imageio.stream.ImageOutputStream
import java.time.LocalDateTime;


@Service
class ImageService(private val imageMapper: ImageMapper) {
    fun getImageUrlById(id: String): GetImageUrlResponse {
        return imageMapper.getImageUrlById(id)
    }

    fun addImage(id: String, rawData: ByteArray): Unit {
        var basePath: String = "/home/ec2-user/images"
        var fileName: String = LocalDateTime.now().toString() + ".jpg" // .jpg only
        var pathToFile: String = basePath + "/" + fileName
        var outputStream: OutputStream = FileOutputStream(pathToFile)
        writeOutputStream(rawData, outputStream, "jpg") // 書き出し
        imageMapper.addImage(id, fileName) // テーブルに記録
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
            param.compressionQuality = 0.5f
        }

        var ios: ImageOutputStream = ImageIO.createImageOutputStream(outputStream)
        writer.output = ios
        var iioImage: IIOImage = IIOImage(buffer, null, null)
        writer.write(null, iioImage, param)
    }
}
