package com.example.apiSample.controller

import com.example.apiSample.service.ImageService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayOutputStream
import java.io.InputStream


data class GetImageUrlResponse(
        var url: String
)
data class GetImageResponse(
        var data: ByteArray
)

@RestController
class ImageController(private val imageService: ImageService) {
    @PostMapping(
            value = ["/image/create/{id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun addImage(@RequestParam("file") file: MultipartFile, @PathVariable("id") id: String): Unit {
        val rawData: ByteArray = file.bytes
        imageService.addImage(id, rawData)
    }

    @GetMapping(
            value = ["/image/{id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getImageUrlById(@PathVariable("id") id: String): GetImageUrlResponse {
        return imageService.getImageUrlById(id)
    }

    @GetMapping(
            value = "/image/{url}",
            produces = [(MediaType.IMAGE_JPEG_VALUE)]
    )
    fun getImageWithMediaType(@PathVariable("url") url: String): GetImageResponse {
        val inputStream: InputStream = this::class.java.getResourceAsStream(url)
        val outputStream = ByteArrayOutputStream()
        inputStream.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
        return GetImageResponse(outputStream.toByteArray())
    }
}
