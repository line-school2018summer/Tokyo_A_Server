package com.example.apiSample.mapper

import com.example.apiSample.model.Friend
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface FriendMapper {
    @Insert(
            """
        INSERT INTO friend_info.friends (user_id, friend_id, room_id)
        VALUES (#{userId}, #{friendId}, #{roomId})
        """
    )
    fun addFriend(userId: String, friendId: String, roomId: Long)

    @Select(
            """
        SELECT * from friend_info.friends
        """
    )
    fun getAllFriends(): ArrayList<Friend>

    @Select(
            """
        SELECT * from friend_info.friends WHERE user_id = #{userId}
        """
    )
    fun getFriendsById(userId: String): ArrayList<Friend>

    @Delete(
            """
        DELETE from friend_info.friends WHERE user_id = #{userId} AND friend_id = #{friendId}
        """
    )
    fun deleteFriend(userId: String, friendId: String)
}