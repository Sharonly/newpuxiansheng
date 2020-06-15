package com.puxiansheng.logic.data.message

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.impl.WorkDatabaseMigrations.MIGRATION_1_2
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.logic.bean.MessageItem
import com.puxiansheng.logic.data.common.CommonRoomDatabase

@Database(
    entities = [MessageItem::class],
    version = 1,
    exportSchema = false
)
abstract class MessageDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao

    companion object {
        private var messageDatabase: MessageDatabase? = null

        fun getInstance(context: Context): MessageDatabase {
            return messageDatabase
                ?: createDatabase(
                    context
                )
        }

        private fun createDatabase(context: Context): MessageDatabase {
            return Room.databaseBuilder(
                context,
                MessageDatabase::class.java,
                MessageDatabase::class.java.name
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}