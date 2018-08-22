package com.example.apiSample.controller

import com.example.apiSample.service.ImageService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


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
            value = ["/image/id/{id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getImageUrlById(@PathVariable("id") id: String): GetImageUrlResponse {
        return imageService.getImageUrlById(id)
    }

    @ResponseBody
    @GetMapping(
            value = "/image/url/{url}",
            produces = [(MediaType.IMAGE_JPEG_VALUE)]
    )
    fun getImageWithMediaType(@PathVariable("url") url: String): ByteArray {
        val path: Path = Paths.get("/home/ec2-user/images/" + url)
        lateinit var byteArray: ByteArray
        try {
            byteArray = Files.readAllBytes(path)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return byteArray
    }
}
