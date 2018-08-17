package com.example.apiSample.mapper

import com.example.apiSample.model.Talk
import com.example.apiSample.model.UserProfile
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import java.sql.Timestamp

@Mapper
interface UserMapper {
    // すべてのユーザの情報を取得する
    @Select(
        """
        SELECT * FROM client_info.clients
        """
    )
    fun getAllUsers(): ArrayList<UserProfile>

    // idでユーザを探す（返り値は単体のはず）
    @Select(
        """
        SELECT id, name, created_at, updated_at FROM client_info.clients WHERE id=#{userId}
        """
    )
    fun findByUserId(userId: String): UserProfile

    // 名前でユーザを探す
    @Select(
        """
        SELECT * FROM client_info.clients WHERE name=#{name}
        """
    )
    fun findBySearchStr(searchStr: String): ArrayList<UserProfile>

    @Select(
        """
        SELECT * FROM client_info.clients WHERE name LIKE CONCAT('%', #{name}, '%')
        """
    )
    fun findByLikelySearchStr(searchStr: String): ArrayList<UserProfile>

    // ユーザの登録名を変更する
    @Insert(
        """
        UPDATE client_info.clients SET name=#{name} WHERE id=#{id}
        """
    )
    fun modifyProfile(id: String, name: String): Unit

    // ユーザの情報を追加する
    @Insert(
        """
        INSERT INTO client_info.clients (id, name)
        VALUES (#{id}, #{name});
        """
    )
    fun addProfile(id: String, name: String): Unit

    // ユーザの情報を削除する
    @Delete(
        """
        DELETE FROM client_info.clients WHERE id=#{id}
        """
    )
    fun deleteProfile(id: String): Unit


    /* --- talk operation ---*/
    @Insert(
        """
        INSERT INTO talk_info.talks (sender_id, send_room_num, text)
        VALUES (#{sender_id}, #{send_room_num}, #{text})
        """
    )
    fun addTalk(sender_id: String, send_room_num: Long, text: String): Unit

    @Select(
        """
        SELECT * from talk_info.talks
        """
    )
    fun getAllTalks(): ArrayList<Talk>

    @Select(
        """
        SELECT * from talk_info.talks WHERE send_room_num=#{receive_room_num} AND talk_id>#{since_talk_id}
        """
    )
    fun getTalk(receive_room_num: Long, since_talk_id: Long): ArrayList<Talk>
}
