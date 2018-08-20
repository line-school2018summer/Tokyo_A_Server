package com.example.apiSample.mapper

import com.example.apiSample.model.Image
import com.example.apiSample.model.SQLImage
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import java.io.ByteArrayInputStream
import java.sql.Blob

@Mapper
interface ImageMapper {
    // すべてのユーザの情報を取得する
    @Select(
        """
        SELECT * FROM media_info.images
        """
    )
    fun getAllImages(): ArrayList<SQLImage>

    // idでユーザを探す（返り値は単体のはず）
    @Select(
        """
        SELECT * FROM media_info.images WHERE id=#{id}
        """
    )
    fun findImageById(id: Long): SQLImage

    // ユーザの情報を追加する
    @Insert(
        """
        INSERT INTO media_info.images (file_name, raw_data)
        VALUES (#{fileName}, #{rawData});
        """
    )
    fun addImage(fileName: String, rawData: Blob): Unit

    /*
    // ユーザの情報を削除する
    @Delete(
        """
        DELETE FROM client_info.clients WHERE id=#{id}
        """
    )
    fun deleteImage(id: String): Unit
    */
}
