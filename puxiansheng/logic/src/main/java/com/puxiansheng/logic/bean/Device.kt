package com.puxiansheng.logic.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_device")
data class Device(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "_id")
    val id: Int = 1,

    @ColumnInfo(name = "_uid")
    val uid: String = "UNKNOWN",

    @ColumnInfo(name = "_manufacturer")
    val manufacturer: String = "UNKNOWN",

    @ColumnInfo(name = "_brand")
    val brand: String = "UNKNOWN",

    @ColumnInfo(name = "_hardware")
    val hardware: String = "UNKNOWN",

    @ColumnInfo(name = "_model")
    val model: String = "UNKNOWN",

    @ColumnInfo(name = "_sdk")
    val sdk: Int = 0,

    @ColumnInfo(name = "_board")
    val board: String = "UNKNOWN",

    @ColumnInfo(name = "_host")
    val host: String = "UNKNOWN"
)