package com.example.apiSample.controller

import com.example.apiSample.model.Friend
import com.example.apiSample.service.FriendService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
class FriendController(private val friendService: FriendService) {
    @PostMapping(
            value = ["/friend/add/{userId}/{friendId}/{roomId}"],
            produces = [MediaType.APPLICATION_JSON_UTF8_VALUE]
    )
    fun addFriend(@PathVariable("userId") userId: String, @PathVariable("friendId") friendId: String, @PathVariable("roomId") roomId: Long): Unit {
        friendService.addFriend(userId, friendId, roomId)
    }

    @GetMapping(
            value = ["/friend"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getAllFriends(): ArrayList<Friend> {
        return friendService.getAllFriends()
    }

    @GetMapping(
            value = ["/friend/{userId}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getFriendsById(@PathVariable("userId") userId: String): ArrayList<Friend> {
        return friendService.getFriendsById(userId)
    }

    @DeleteMapping(
            value = ["/friend/delete/{userId}/{friendId}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deleteFriend(@PathVariable("userId") userId: String ,@PathVariable("friendId") friendId: String) {
        friendService.deleteFriend(userId, friendId)
    }
}
