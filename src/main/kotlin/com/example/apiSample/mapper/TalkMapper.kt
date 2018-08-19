package com.example.apiSample.mapper

import com.example.apiSample.model.Talk
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface TalkMapper {
    @Insert(
        """
        INSERT INTO talk_info.talks (sender_id, room_id, text)
        VALUES (#{senderId}, #{roomId}, #{text})
        """
    )
    fun addTalk(senderId: String, roomId: Long, text: String): Unit

    @Select(
        """
        SELECT * from talk_info.talks
        """
    )
    fun getAllTalks(): ArrayList<Talk>

    @Select(
        """
        SELECT * from talk_info.talks WHERE room_id=#{roomId} AND talk_id>#{sinceTalkId}
        """
    )
    fun getTalk(roomId: Long, sinceTalkId: Long): ArrayList<Talk>

    @Select(
        """
        SELECT * from talk_info.talks WHERE talk_id<=#{talkId}
        """
    )
    fun getTalkBeforeId(talkId: Long): ArrayList<Talk>

    @Delete(
        """
        DELETE from talk_info.talks WHERE talk_id<=#{talkId}
        """
    )
    fun deleteTalkBeforeId(talkId: Long)
}
