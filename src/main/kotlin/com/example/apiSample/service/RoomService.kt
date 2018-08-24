package com.example.apiSample.service

import com.example.apiSample.mapper.RoomMapper
import com.example.apiSample.model.Room
import com.example.apiSample.model.RoomMember
import org.springframework.stereotype.Service

@Service
class RoomService(private val RoomMapper: RoomMapper) {
    /* ROOMS */
    // すべてのルームの情報を取得する
    fun getAllRooms(): ArrayList<Room> {
        return RoomMapper.getAllRooms()
    }

    // idでルームを探す
    fun getRoomById(roomId: Long): Room {
        return RoomMapper.findByRoomId(roomId)
    }

    // ルームの情報を追加する
    fun addRoom(roomName: String): Unit {
        RoomMapper.addRoom(roomName)
    }

    // ルームの登録名を変更する
    fun modifyRoom(roomId: Long, roomName: String): Unit {
        RoomMapper.modifyRoom(roomId, roomName)
    }

    // ルームの情報を削除する
    fun deleteRoom(roomId: Long): Unit {
        RoomMapper.deleteRoom(roomId)
    }

    /* ROOM MEMBERS */
    // すべてのルームメンバーの情報を取得する
    fun getAllRoomMembers(): ArrayList<RoomMember> {
        return RoomMapper.getAllRoomMembers()
    }

    // idでルームを探す
    fun getRoomMembersByRoomId(roomId: Long): ArrayList<RoomMember> {
        return RoomMapper.findMembersByRoomId(roomId)
    }

    // idでルームを探す
    fun getRoomMembersByRoomIdAndUserId(roomId: Long, uid: String): ArrayList<RoomMember> {
        return RoomMapper.findRoomMembersByRoomIdAndUserId(roomId, uid)
    }

    // idでルームを探す
    fun getRoomsByUserId(uid: String): ArrayList<RoomMember> {
        return RoomMapper.findRoomsByUserId(uid)
    }

    // ルームの情報を追加する
    fun addRoomMember(roomId: Long, uid: String): Unit {
        RoomMapper.addRoomMember(roomId, uid)
    }

    // ルームの情報を削除する
    fun deleteRoomMember(roomId: Long, uid: String): Unit {
        RoomMapper.deleteRoomMember(roomId, uid)
    }
}