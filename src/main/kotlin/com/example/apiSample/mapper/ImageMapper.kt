package com.example.apiSample.mapper

import com.example.apiSample.model.ImageUrl
import org.apache.ibatis.annotations.*

@Mapper
interface ImageMapper {
    // すべての画像URLを取得する
    @Select(
        """
        SELECT * FROM media_info.images
        """
    )
    fun getAllImageUrl(): ArrayList<ImageUrl>

    // idで画像URLを探す
    @Select(
        """
        SELECT * FROM media_info.images WHERE uid=#{uid}
        """
    )
    fun getImageUrlById(uid: String): ImageUrl?

    // 画像URLを追加する
    @Insert(
        """
        INSERT INTO media_info.images (uid, path_to_file)
        VALUES (#{uid}, #{pathToFile})
        """
    )
    fun addImage(uid: String, pathToFile: String): Unit

    // 画像URLを変更する
    @Update(
        """
        UPDATE media_info.images SET path_to_file=#{pathToFile} WHERE uid=#{uid}
        """
    )
    fun modifyImage(uid: String, pathToFile: String): Unit

    // 画像URLを削除する
    @Delete(
        """
        DELETE FROM media_info.images WHERE uid=#{uid}
        """
    )
    fun deleteImage(uid: String): Unit
}
