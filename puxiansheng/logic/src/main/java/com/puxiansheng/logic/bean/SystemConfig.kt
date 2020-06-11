package com.puxiansheng.logic.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_sys_cfg")
data class SystemConfig(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "_id")
    val id: Int = 1,

    @ColumnInfo(name = "_max_upload_file_size")
    @SerializedName("max_file_length")
    val maxUploadFileLength: Long = 0,

    @ColumnInfo(name = "_max_upload_file")
    @SerializedName("max_upload")
    val maxUploadFile: Int = 6,

    @ColumnInfo(name = "_network_connect_read_timeout")
    @SerializedName("conn_read_time_out")
    val networkConnectReadTimeout: Int = 0,

    @ColumnInfo(name = "_network_connect_write_timeout")
    @SerializedName("conn_write_time_out")
    val networkConnectWriteTimeout: Int = 0,

    @ColumnInfo(name = "_network_connect_timeout")
    @SerializedName("conn_time_out")
    val networkConnectTimeout: Int = 0,

    @ColumnInfo(name = "_def_user_icon")
    @SerializedName("default_header_img")
    val defaultUserIcon: String = ""


)