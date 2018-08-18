package com.example.apiSample.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Timestamp

data class UserProfile(
        var id: String,
        var name: String,
        @get:JsonProperty("createdAt") var createdAt: Timestamp,
        @get:JsonProperty("updatedAt") var updatedAt: Timestamp
)

/*
data class UserList(
        var id: Long,
        var name: String,
        @get:JsonProperty("created_at") var createdAt: Timestamp,
        @get:JsonProperty("updated_at") var updatedAt: Timestamp
)
*/

data class Talk(
        var talkId: Long,
        var senderId: String,
        var roomId: Long,
        var text: String,
        var numRead: Long,
        @get:JsonProperty("createdAt") var createdAt: Timestamp,
        @get:JsonProperty("updatedAt") var updatedAt: Timestamp
)