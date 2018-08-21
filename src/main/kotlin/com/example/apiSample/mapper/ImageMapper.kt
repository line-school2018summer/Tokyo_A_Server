package com.example.apiSample.mapper

import com.example.apiSample.controller.GetImageUrlResponse
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface ImageMapper {
    // idで画像を探す
    @Select(
        """
        SELECT path_to_file FROM media_info.images WHERE uid=#{uid}
        """
    )
    fun getImageUrlById(uid: String): GetImageUrlResponse

    // 画像を追加する
    @Insert(
        """
        INSERT INTO media_info.images (uid, path_to_file)
        VALUES (#{uid}, #{pathToFile});
        """
    )
    fun addImage(uid: String, pathToFile: String): Unit

    // 画像を削除する
    @Delete(
        """
        DELETE FROM client_info.clients WHERE uid=#{uid}
        """
    )
    fun deleteImage(uid: String): Unit
}
