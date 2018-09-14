package com.example.apiSample.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Timestamp

data class UserProfile(
        var id: String,
        var name: String,
        @get:JsonProperty("created_at") var createdAt: Timestamp,
        @get:JsonProperty("updated_at") var updatedAt: Timestamp
)

data class Talk(
        @get:JsonProperty("talk_id") var talkId: Long,
        @get:JsonProperty("sender_id") var senderId: String,
        @get:JsonProperty("room_id") var roomId: Long,
        var text: String,
        @get:JsonProperty("num_read") var numRead: Long,
        @get:JsonProperty("created_at") var createdAt: Timestamp,
        @get:JsonProperty("updated_at") var updatedAt: Timestamp
)

data class Friend(
        @get:JsonProperty("user_id") var userId: String,
        @get:JsonProperty("friend_id") var friendId: String,
        @get:JsonProperty("created_at") var createdAt: Timestamp,
        @get:JsonProperty("updated_at") var updatedAt: Timestamp
)

data class Room(
        @get:JsonProperty("room_id") var roomId: String,
        @get:JsonProperty("room_name") var roomName: String,
        @get:JsonProperty("created_at") var createdAt: Timestamp,
        @get:JsonProperty("updated_at") var updatedAt: Timestamp
)

data class RoomMember(
        @get:JsonProperty("room_id") var roomId: String,
        @get:JsonProperty("uid") var uid: String,
        @get:JsonProperty("created_at") var createdAt: Timestamp,
        @get:JsonProperty("updated_at") var updatedAt: Timestamp
)

data class ImageUrl(
        var uid: String,
        @get:JsonProperty("path_to_file") var pathToFile: String,
        @get:JsonProperty("created_at") var createdAt: Timestamp,
        @get:JsonProperty("updated_at") var updatedAt: Timestamp
)