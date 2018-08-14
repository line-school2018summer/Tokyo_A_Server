package com.example.apiSample.controller

import com.example.apiSample.model.UserProfile
import com.example.apiSample.service.UserProfileService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

/*
data class UserListResponse(
        var id: Long,
        var name: String
)

data class PostSearchRequest(
        val search_str: String
)
*/

@RestController
class UserController(private val userProfileService: UserProfileService) {
    // GETに相当する機能; client情報の取得
    @GetMapping(
            value = ["/user"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getAllUsers(): ArrayList<UserProfile> = userProfileService.getAllUsers()

    @GetMapping(
            value = ["/user/id/{id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getUserById(@PathVariable("id" ) userId: String): UserProfile {
        return userProfileService.getUserById(userId)
    }

    @GetMapping(
            value = ["/user/name/{name}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getUsersByName(@PathVariable("name" ) name: String): ArrayList<UserProfile> {
        return userProfileService.getUsersByName(name)
    }

    @GetMapping(
            value = ["/user/likelyname/{name}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getUsersByLikelyName(@PathVariable("name" ) name: String): ArrayList<UserProfile> {
        return userProfileService.getUsersByLikelyName(name)
    }

    /*
    @PostMapping(
            value = ["/user/search"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getList(@RequestBody request: PostSearchRequest): Map<String, List<UserListResponse>> {
        val userList: ArrayList<UserProfile> = userProfileService.findUsersList(request.search_str)
        return mapOf("results" to userList.map {
            UserListResponse(
                    id = it.id,
                    name = it.name
            )
        })
    }
    */

    // POSTに相当する機能; clientの追加
    @PostMapping(
            value = ["/user/create/{id}/{name}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun addProfile(@PathVariable("id") id: String, @PathVariable("name") name: String): Unit {
        userProfileService.addProfile(id, name)
    }

    // PUTに相当する機能; clientの名前の変更
    @PutMapping(
            value = ["/user/modify/{id}/{name}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun modifyProfile(@PathVariable("id") id: String, @PathVariable("name") name: String): Unit {
        userProfileService.modifyProfile(id, name)
    }

    // DELETEに相当する機能; clientの削除
    @DeleteMapping(
            value = ["/user/delete/{id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deleteProfile(@PathVariable("id") id: String): UserProfile {
        val deleteList: UserProfile = userProfileService.getUserById(id)
        userProfileService.deleteProfile(id)
        return deleteList // 削除したデータのリストを返す
    }


}
