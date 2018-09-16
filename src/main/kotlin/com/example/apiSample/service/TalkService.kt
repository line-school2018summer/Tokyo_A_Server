package com.example.apiSample.service

import com.example.apiSample.mapper.TalkMapper
import com.example.apiSample.model.Talk
import org.springframework.stereotype.Service

@Service
class TalkService(private val talkMapper: TalkMapper) {
    fun addTalk(senderId: String, roomId: String, text: String): Unit {
        talkMapper.addTalk(senderId, roomId, text)
    }

    fun getAllTalks(): ArrayList<Talk> {
        return talkMapper.getAllTalks()
    }

    fun getTalk(roomId: String, sinceTalkId: Long): ArrayList<Talk> {
        return talkMapper.getTalk(roomId, sinceTalkId)
    }

    fun getTalkBeforeId(talkId: Long): ArrayList<Talk> {
        return talkMapper.getTalkBeforeId(talkId)
    }

    fun deleteTalkBeforeId(talkId: Long): Unit {
        talkMapper.deleteTalkBeforeId(talkId)
    }
}