package com.example.apiSample.service

import com.example.apiSample.mapper.ImageMapper
import com.example.apiSample.model.Image
import org.springframework.stereotype.Service
import java.sql.Blob


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

    fun addImage(fileName: String, rawData: Blob): Unit {
        imageMapper.addImage(fileName, rawData)
    }

    /*
    fun deleteImage(id: Long): Unit {
        imageMapper.deleteImage(id)
    }
    */
}
