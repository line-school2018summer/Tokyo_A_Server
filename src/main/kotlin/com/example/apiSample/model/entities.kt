package com.example.apiSample.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Blob
import java.sql.Timestamp

data class UserProfile(
        var id: String,
        var name: String,
        @get:JsonProperty("created_at") var createdAt: Timestamp,
        @get:JsonProperty("updated_at") var updatedAt: Timestamp
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
        @get:JsonProperty("talk_id") var talkId: Long,
        @get:JsonProperty("sender_id") var senderId: String,
        @get:JsonProperty("room_id") var roomId: Long,
        var text: String,
        @get:JsonProperty("num_read") var numRead: Long,
        @get:JsonProperty("created_at") var createdAt: Timestamp,
        @get:JsonProperty("updated_at") var updatedAt: Timestamp
)

data class Image(
        var id: Long,
        @get:JsonProperty("file_name") var fileName: String,
        @get:JsonProperty("raw_data") var rawData: ByteArray
)

data class SQLImage(
        var id: Long,
        @get:JsonProperty("file_name") var fileName: String,
        @get:JsonProperty("raw_data") var rawData: Blob
)