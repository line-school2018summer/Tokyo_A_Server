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
    fun getRoomById(roomId: String): Room? {
        return RoomMapper.findByRoomId(roomId)
    }

    // ルームの情報を追加する
    fun addRoom(roomId: String, roomName: String): Unit {
        if(getRoomById(roomId) == null) {
            RoomMapper.addRoom(roomId, roomName, false)
        }
    }

    // ルームの情報を追加する
    fun addRoomAsGroup(roomId: String, roomName: String): Unit {
        if(getRoomById(roomId) == null) {
            RoomMapper.addRoom(roomId, roomName, true)
        }
    }

    // ルームの登録名を変更する
    fun modifyRoom(roomId: String, roomName: String): Unit {
        RoomMapper.modifyRoom(roomId, roomName)
    }

    // ルームの情報を削除する
    fun deleteRoom(roomId: String): Unit {
        RoomMapper.deleteRoom(roomId)
    }

    /* ROOM MEMBERS */
    // すべてのルームメンバーの情報を取得する
    fun getAllRoomMembers(): ArrayList<RoomMember> {
        return RoomMapper.getAllRoomMembers()
    }

    // idでルームを探す
    fun getRoomMembersByRoomId(roomId: String): ArrayList<RoomMember> {
        return RoomMapper.findMembersByRoomId(roomId)
    }

    // idでルームを探す
    fun getRoomMembersByRoomIdAndUserId(roomId: String, uid: String): ArrayList<RoomMember> {
        return RoomMapper.findRoomMembersByRoomIdAndUserId(roomId, uid)
    }

    // idでルームを探す
    fun getRoomsByUserId(uid: String): ArrayList<RoomMember> {
        return RoomMapper.findRoomsByUserId(uid)
    }

    // ルームの情報を追加する
    fun addRoomMember(roomId: String, uid: String): Unit {
        if(getRoomMembersByRoomIdAndUserId(roomId, uid).size == 0) {
            RoomMapper.addRoomMember(roomId, uid)
        }
    }

    // ルームの情報を削除する
    fun deleteAllRoomMembers(roomId: String): Unit {
        RoomMapper.deleteAllRoomMembers(roomId)
    }

    // ルームの情報を削除する
    fun deleteRoomMember(roomId: String, uid: String): Unit {
        RoomMapper.deleteRoomMember(roomId, uid)
    }
}