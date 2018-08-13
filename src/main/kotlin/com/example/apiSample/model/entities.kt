package com.example.apiSample.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Timestamp

data class UserProfile(
        var id: Long,
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
        var sender_id: Long,
        var recever_id: Long,
        var text: String,
        @get:JsonProperty("created_at") var createdAt: Timestamp,
        @get:JsonProperty("updated_at") var updatedAt: Timestamp
)