package com.example.apiSample.controller

import com.example.apiSample.model.ImageUrl
import com.example.apiSample.service.ImageService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.sql.Timestamp


@RestController
class ImageController(private val imageService: ImageService) {
    @GetMapping(
            value = ["/image"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getAllImageUrl(): ArrayList<ImageUrl> {
        return imageService.getAllImageUrl()
    }

    @GetMapping(
            value = ["/image/id/{id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getImageUrlById(@PathVariable("id") id: String): ImageUrl {
        val imageUrl = imageService.getImageUrlById(id)
        if(imageUrl != null) {
            return imageUrl
        } else {
            return ImageUrl("$id", "default.jpg", Timestamp(0), Timestamp(0))
        }
    }

    @ResponseBody
    @GetMapping(
            value = ["/image/url/{url}"],
            produces = [(MediaType.IMAGE_JPEG_VALUE)]
    )
    fun getImageByUrl(@PathVariable("url") url: String): ByteArray {
        return imageService.getImageByUrl(url)
    }

    @PostMapping(
            value = ["/image/create/{id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun addImage(@RequestParam("file") file: MultipartFile, @PathVariable("id") id: String): Unit {
        val rawData: ByteArray = file.bytes
        imageService.addOrModifyImage(id, rawData)
    }

    @PostMapping(
            value = ["/image/create/default/{id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun addDefaultImage(@PathVariable("id") id: String): Unit {
        imageService.addDefaultImage(id)
    }

    @PutMapping(
            value = ["/image/modify/{id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun modifyImage(@RequestParam("file") file: MultipartFile, @PathVariable("id") id: String): Unit {
        val rawData: ByteArray = file.bytes
        imageService.addOrModifyImage(id, rawData)
    }

    @DeleteMapping(
            value = ["/image/delete/{id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deleteImage(@PathVariable("id") id: String): Unit {
        imageService.deleteImage(id)
    }
}
