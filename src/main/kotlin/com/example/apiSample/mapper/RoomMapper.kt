package com.example.apiSample.mapper

import com.example.apiSample.model.Room
import com.example.apiSample.model.RoomMember
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface RoomMapper {
    /* ROOMS */
    // すべてのルーム情報を取得する
    @Select(
        """
        SELECT * FROM room_info.rooms
        """
    )
    fun getAllRooms(): ArrayList<Room>

    // idでルームを探す（返り値は単体のはず）
    @Select(
        """
        SELECT * FROM room_info.rooms WHERE room_id=#{roomId}
        """
    )
    fun findByRoomId(roomId: Long): Room

    // ルームの登録名を変更する
    @Insert(
        """
        UPDATE room_info.rooms SET room_name=#{roomName} WHERE room_id=#{roomId}
        """
    )
    fun modifyRoom(roomId: Long, roomName: String): Unit

    // ルームの情報を追加する
    @Insert(
        """
        INSERT INTO room_info.rooms (room_name)
        VALUES (#{roomName})
        """
    )
    fun addRoom(roomName: String): Unit

    // ユーザの情報を削除する
    @Delete(
        """
        DELETE FROM room_info.rooms WHERE room_id=#{roomId}
        """
    )
    fun deleteRoom(roomId: Long): Unit


    /* ROOM MEMBERS */
    // すべてのルームメンバー情報を取得する
    @Select(
        """
        SELECT * FROM room_info.room_members
        """
    )
    fun getAllRoomMembers(): ArrayList<RoomMember>

    // ルームidでメンバーを探す
    @Select(
        """
        SELECT * FROM room_info.room_members WHERE room_id=#{roomId}
        """
    )
    fun findMembersByRoomId(roomId: Long): ArrayList<RoomMember>

    // ユーザidでルームを探す
    @Select(
        """
        SELECT * FROM room_info.room_members WHERE uid=#{uid}
        """
    )
    fun findRoomsByUserId(uid: String): ArrayList<RoomMember>

    // ルームidとユーザidでルームを探す
    @Select(
        """
        SELECT * FROM room_info.room_members WHERE room_id=#{roomId} AND uid=#{uid}
        """
    )
    fun findRoomMembersByRoomIdAndUserId(roomId: Long, uid: String): ArrayList<RoomMember>

    // ルームメンバーの情報を追加する
    @Insert(
        """
        INSERT INTO room_info.room_members (room_id, uid)
        VALUES (#{roomId}, #{uid})
        """
    )
    fun addRoomMember(roomId: Long, uid: String): Unit

    // あるルームのルームメンバーの情報をすべて削除する
    @Delete(
        """
        DELETE FROM room_info.room_members WHERE room_id=#{roomId}
        """
    )
    fun deleteAllRoomMembers(roomId: Long): Unit

    // ルームメンバーの情報を削除する
    @Delete(
        """
        DELETE FROM room_info.room_members WHERE room_id=#{roomId} AND uid=#{uid}
        """
    )
    fun deleteRoomMember(roomId: Long, uid: String): Unit
}