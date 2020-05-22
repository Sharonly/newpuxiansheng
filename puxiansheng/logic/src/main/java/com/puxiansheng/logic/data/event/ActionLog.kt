package com.puxiansheng.logic.data.event

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_action_log")
data class ActionLog (@PrimaryKey @ColumnInfo(name = "_id") val id : Long)