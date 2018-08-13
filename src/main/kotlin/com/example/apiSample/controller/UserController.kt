package com.example.apiSample.controller

import com.example.apiSample.model.UserProfile
import com.example.apiSample.service.UserProfileService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

data class UserListResponse(
        var id: Long,
        var name: String
)

data class PostSearchRequest(
        val search_str: String
)

@RestController
class UserController(private val userProfileService: UserProfileService) {
    @GetMapping(
            value = ["/user"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getAllUsers(): ArrayList<UserProfile> = userProfileService.getAllUsers()

    @GetMapping(
            value = ["/user/id/{id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getProfile(@PathVariable("id" ) userId: Long): UserProfile {
        return userProfileService.getProfile(userId)
    }

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

    // PUTに相当する機能; clientの追加
    @PutMapping(
            value = ["/user/name/{name}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun putProfile(@PathVariable("name") name: String): Unit {
        // val new_id =
                userProfileService.putProfile(name)
        // return userProfileService.getProfile(new_id)
    }

    // DELETEに相当する機能; clientの削除
    @DeleteMapping(
            value = ["/user/name/{name}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deleteProfile(@PathVariable("name") name: String): ArrayList<UserProfile> {
        val deleteList: ArrayList<UserProfile> = userProfileService.findUsersList(name)
        userProfileService.deleteProfile(name)
        return deleteList // 削除したデータのリストを返す
    }


}
