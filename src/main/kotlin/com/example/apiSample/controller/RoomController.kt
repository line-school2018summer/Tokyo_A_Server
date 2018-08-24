package com.example.apiSample.controller

import com.example.apiSample.model.Room
import com.example.apiSample.model.RoomMember
import com.example.apiSample.service.RoomService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class RoomController(private val RoomService: RoomService) {
    /* ROOMS */
    // GETに相当する機能; room情報の取得
    @GetMapping(
            value = ["/room"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getAllRooms(): ArrayList<Room> = RoomService.getAllRooms()

    @GetMapping(
            value = ["/room/room_id/{room_id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getRoomById(@PathVariable("room_id" ) roomId: Long): Room {
        return RoomService.getRoomById(roomId)
    }

    // POSTに相当する機能; roomの追加
    @PostMapping(
            value = ["/room/create/{room_name}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun addProfile(@PathVariable("room_name") roomName: String): Unit {
        RoomService.addRoom(roomName)
    }

    // PUTに相当する機能; roomの名前の変更
    @PutMapping(
            value = ["/room/modify/{room_id}/{room_name}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun modifyProfile(@PathVariable("room_id") roomId: Long, @PathVariable("room_name") roomName: String): Unit {
        RoomService.modifyRoom(roomId, roomName)
    }

    // DELETEに相当する機能; roomの削除
    @DeleteMapping(
            value = ["/room/delete/{room_id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deleteProfile(@PathVariable("room_id") roomId: Long): Room {
        val deleteList: Room = RoomService.getRoomById(roomId)
        RoomService.deleteRoom(roomId)
        return deleteList // 削除したデータのリストを返す
    }


    /* ROOM MEMBERS */
    // GETに相当する機能; RoomMember情報の取得
    @GetMapping(
            value = ["/room_member"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getAllRoomMembers(): ArrayList<RoomMember> = RoomService.getAllRoomMembers()

    @GetMapping(
            value = ["/room_member/room_id/{room_id}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getRoomMembersByRoomId(@PathVariable("room_id" ) roomId: Long): ArrayList<RoomMember> {
        return RoomService.getRoomMembersByRoomId(roomId)
    }

    @GetMapping(
            value = ["/room_member/uid/{uid}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun getRoomMembersByUserId(@PathVariable("uid" ) uid: String): ArrayList<RoomMember> {
        return RoomService.getRoomsByUserId(uid)
    }

    // POSTに相当する機能; RoomMemberの追加
    @PostMapping(
            value = ["/room_member/create/{room_id}/{uid}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun addProfile(@PathVariable("room_id") roomId: Long, @PathVariable("uid") uid: String): Unit {
        RoomService.addRoomMember(roomId, uid)
    }

    // DELETEに相当する機能; RoomMemberの削除
    @DeleteMapping(
            value = ["/room_member/delete/{room_id}/{uid}"],
            produces = [(MediaType.APPLICATION_JSON_UTF8_VALUE)]
    )
    fun deleteRoomMembers(@PathVariable("room_id") roomId: Long, @PathVariable("uid") uid: String): ArrayList<RoomMember> {
        val deleteList: ArrayList<RoomMember> = RoomService.getRoomMembersByRoomIdAndUserId(roomId, uid)
        RoomService.deleteRoomMember(roomId, uid)
        return deleteList // 削除したデータのリストを返す
    }
}