package com.example.apiSample.mapper

import com.example.apiSample.model.UserProfile
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

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
        SELECT * FROM client_info.clients WHERE id=#{userId}
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
}
