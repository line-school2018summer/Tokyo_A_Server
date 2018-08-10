package com.example.apiSample.mapper

import com.example.apiSample.model.UserProfile
import com.example.apiSample.model.UserList
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface UserMapper {
    @Select(
        """
        SELECT id, name, created_at, updated_at FROM client_info.clients WHERE id=#{userId}
        """
    )
    fun findByUserId(userId: Long): UserProfile

    @Select(
        """
        SELECT id, name FROM client_info.clients WHERE name=#{name}
        """
        // SELECT id, name FROM client_info.clients WHERE name LIKE CONCAT('%', #{searchStr}, '%')
    )
    fun findBySearchStr(searchStr: String): ArrayList<UserList>

    @Insert(
        """
        INSERT INTO client_info.clients (name)
        VALUES (#{name});
        """
    )
    fun addClient(name: String): Unit

    @Delete(
        """
        DELETE FROM client_info.clients WHERE name=#{name}
        """
    )
    fun deleteClient(name: String): Unit
}
