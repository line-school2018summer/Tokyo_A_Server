package com.example.apiSample.controller

import com.example.apiSample.model.UserProfile
import com.example.apiSample.model.UserList
import com.example.apiSample.service.UserProfileService
import com.example.apiSample.service.UserService
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
class UserController(private val userProfileService: UserProfileService, private val userService: UserService) {
    @GetMapping(
            value = ["/user"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun hello(): String{
        return "{\"greeting\": \"Hello World!\"}"
    }

    @GetMapping(
            value = ["/user/{id}/profile"],
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
        val userList: ArrayList<UserList> = userService.findUsersList(request.search_str)
        return mapOf("results" to userList.map {
            UserListResponse(
                    id = it.id,
                    name = it.name
            )
        })
    }

    // PUT に相当する機能
    @PutMapping(
            value = ["/name/{name}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun putProfile(@PathVariable("name") name: String): Unit {
        // val new_id =
                userProfileService.putProfile(name)
        // return userProfileService.getProfile(new_id)
    }

    // DELETE に相当する機能
    @DeleteMapping(
            value = ["/name/{name}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deleteProfile(@PathVariable("name") name: String): ArrayList<UserList> {
        val deleteList: ArrayList<UserList> = userService.findUsersList(name)
        userProfileService.deleteProfile(name)
        return deleteList
    }


}
