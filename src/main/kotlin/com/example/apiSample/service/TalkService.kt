package com.example.apiSample.service

import com.example.apiSample.mapper.TalkMapper
import com.example.apiSample.model.Talk
import org.springframework.stereotype.Service

@Service
class TalkService(private val talkMapper: TalkMapper) {
    fun addTalk(senderId: String, RoomId: Long, text: String): Unit {
        talkMapper.addTalk(senderId, RoomId, text)
    }

    fun getAllTalks(): ArrayList<Talk> {
        return talkMapper.getAllTalks()
    }

    fun getTalk(roomId: Long, sinceTalkId: Long): ArrayList<Talk> {
        return talkMapper.getTalk(roomId, sinceTalkId)
    }

    fun getTalkBeforeId(talkId: Long): ArrayList<Talk> {
        return talkMapper.getTalkBeforeId(talkId)
    }

    fun deleteTalkBeforeId(talkId: Long): Unit {
        talkMapper.deleteTalkBeforeId(talkId)
    }
}