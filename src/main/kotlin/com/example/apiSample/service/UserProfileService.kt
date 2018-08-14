package com.example.apiSample.service

import com.example.apiSample.mapper.UserMapper
import com.example.apiSample.model.UserProfile
import org.springframework.stereotype.Service

@Service
class UserProfileService(private val userMapper: UserMapper) {
    // すべてのユーザの情報を取得する
    fun getAllUsers(): ArrayList<UserProfile> {
        return userMapper.getAllUsers()
    }

    // idでユーザを探す
    fun getUserById(userId: String): UserProfile {
        val profile = userMapper.findByUserId(userId)
        return profile
    }

    // 名前でユーザを探す
    fun getUsersByName(searchStr: String): ArrayList<UserProfile> {
        return userMapper.findBySearchStr(searchStr)
    }

    fun getUsersByLikelyName(searchStr: String): ArrayList<UserProfile> {
        return userMapper.findByLikelySearchStr(searchStr)
    }

    // ユーザの情報を追加する
    fun addProfile(id: String, name: String): Unit {
        userMapper.addProfile(id, name)
    }

    // ユーザの登録名を変更する
    fun modifyProfile(id: String, name: String): Unit {
        userMapper.modifyProfile(id, name)
    }

    // ユーザの情報を削除する
    fun deleteProfile(id: String): Unit {
        userMapper.deleteProfile(id)
    }

}
