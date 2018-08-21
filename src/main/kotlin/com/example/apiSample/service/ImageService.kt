package com.example.apiSample.service

import com.example.apiSample.mapper.ImageMapper
import com.example.apiSample.model.Image
import org.springframework.stereotype.Service
import java.sql.Blob
import java.sql.Statement


@Service
class ImageService(private val imageMapper: ImageMapper) {
    fun getAllImages(): ArrayList<Image> {
        val sqlImageList = imageMapper.getAllImages()
        return ArrayList(sqlImageList.map { image -> Image(image.id, image.fileName, image.rawData.getBytes(1, image.rawData.length().toInt()))})
    }

    fun getImageById(id: Long): Image {
        val sqlImage = imageMapper.findImageById(id)
        val length = sqlImage.rawData.length().toInt()
        return Image(sqlImage.id, sqlImage.fileName, sqlImage.rawData.getBytes(1, length))
    }

    fun getBlobById(id: Long): Image {
        val length = imageMapper.findBlobById(id).length().toInt()
        val byteArray: ByteArray = imageMapper.findBlobById(id).getBytes(1, length)
        return Image(id, "hoge", byteArray)
    }

    fun addImage(fileName: String, rawData: Blob): Unit {
        imageMapper.addImage(fileName, rawData)
    }

    /*
    fun deleteImage(id: Long): Unit {
        imageMapper.deleteImage(id)
    }
    */
}
