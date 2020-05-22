package com.puxiansheng.logic.data.common

import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


abstract class CommonRoomDatabase: RoomDatabase() {

   val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) { // Since we didn't alter the table, there's nothing else to do here.
        }
    }
}