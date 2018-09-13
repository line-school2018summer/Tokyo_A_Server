package com.example.apiSample.controller

import com.example.apiSample.model.Talk
import com.example.apiSample.service.TalkService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.sql.Timestamp


@RestController
class TalkController(private val talkService: TalkService) {
    @PostMapping(
            value = ["/talk/create/{senderId}/{roomId}/{text}"],
            produces = [MediaType.APPLICATION_JSON_UTF8_VALUE]
    )
    fun addTalk(@PathVariable("senderId") senderId: String, @PathVariable("roomId") roomId: Long, @PathVariable("text") text: String): Unit {
        talkService.addTalk(senderId, roomId, text)
    }

    @GetMapping(
            value = ["/talk"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getAllTalks(): ArrayList<Talk> {
        return talkService.getAllTalks()
    }

    @GetMapping(
            value = ["/talk/{roomId}/{sinceTalkId}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getTalk(@PathVariable("roomId") roomId: Long, @PathVariable("sinceTalkId") sinceTalkId: Long): ArrayList<Talk> {
        val timestamp = Timestamp(System.currentTimeMillis())
        while(Timestamp(System.currentTimeMillis()).time - timestamp.time < 5000) {
            if(!talkService.getTalk(roomId, sinceTalkId).isEmpty()){
                return talkService.getTalk(roomId, sinceTalkId)
            }
        }
        return ArrayList()
    }

    @DeleteMapping(
            value = ["/talk/delete/before/{talkId}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deleteTalkBeforeId(@PathVariable("talkId") talkId: Long): ArrayList<Talk> {
        val deleteList: ArrayList<Talk> = talkService.getTalkBeforeId(talkId)
        talkService.deleteTalkBeforeId(talkId)
        return deleteList // 削除したデータのリストを返す
    }
}
