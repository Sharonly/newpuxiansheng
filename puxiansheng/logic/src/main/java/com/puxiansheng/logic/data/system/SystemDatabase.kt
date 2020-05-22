package com.puxiansheng.logic.data.system

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.puxiansheng.logic.bean.SignatureToken
import com.puxiansheng.logic.bean.SystemConfig

@Database(
    entities = [SystemConfig::class],
    version = 2,
    exportSchema = false
)
abstract class SystemDatabase : RoomDatabase() {
    abstract fun systemConfigDao(): SystemDao

    companion object {
        private var systemDatabase: SystemDatabase? = null

        fun getInstance(context: Context): SystemDatabase {
            return systemDatabase
                ?: createDatabase(
                    context
                )
        }

        private fun createDatabase(context: Context): SystemDatabase {
            return Room.databaseBuilder(
                context,
                SystemDatabase::class.java,
                SystemDatabase::class.java.name
            )
//                .fallbackToDestructiveMigration()
                .addMigrations(object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                    }
                })//版本升级为2，解决错误的升级数据库。导致灰度期间出现了不少crash
                .build()
        }
    }
}