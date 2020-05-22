package com.puxiansheng.logic.data.info

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.impl.WorkDatabaseMigrations.MIGRATION_1_2
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.logic.data.common.CommonRoomDatabase

@Database(
    entities = [InfoItem::class],
    version = 2,
    exportSchema = false
)
abstract class InfoDatabase : RoomDatabase() {
    abstract fun infoDao(): InfoDao

    companion object {
        private var infoDatabase: InfoDatabase? = null

        fun getInstance(context: Context): InfoDatabase {
            return infoDatabase
                ?: createDatabase(
                    context
                )
        }

        private fun createDatabase(context: Context): InfoDatabase {
            return Room.databaseBuilder(
                context,
                InfoDatabase::class.java,
                InfoDatabase::class.java.name
            )
//                .fallbackToDestructiveMigration()
                .addMigrations(
                    object : Migration(1, 2) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                        }
                    })//版本升级为2，解决错误的升级数据库。导致灰度期间出现了不少crash
                .build()
        }
    }
}