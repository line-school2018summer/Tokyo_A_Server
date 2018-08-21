package com.example.apiSample.service

import com.example.apiSample.mapper.FriendMapper
import com.example.apiSample.model.Friend
import org.springframework.stereotype.Service

@Service
class FriendService(private val friendMapper: FriendMapper) {
    fun addFriend(userId: String, friendId: String, roomId: Long) {
        friendMapper.addFriend(userId, friendId, roomId)
    }

    fun getAllFriends(): ArrayList<Friend> {
        return friendMapper.getAllFriends()
    }

    fun getFriendsById(userId: String): ArrayList<Friend> {
       return friendMapper.getFriendsById(userId)
    }

    fun deleteFriend(userId: String ,friendId: String) {
        friendMapper.deleteFriend(userId, friendId)
    }
}