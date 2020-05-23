package com.puxiansheng.logic.data.business

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.impl.WorkDatabaseMigrations.MIGRATION_1_2
import com.puxiansheng.logic.bean.BusinessBean

@Database(
    entities = [BusinessBean::class],
    version = 2,
    exportSchema = false
)
abstract class BusinessDatabase : RoomDatabase() {
    abstract fun businessDao(): BusinessDao

    companion object {
        private var infoDatabase: BusinessDatabase? = null

        fun getInstance(context: Context): BusinessDatabase {
            return infoDatabase
                ?: createDatabase(
                    context
                )
        }

        private fun createDatabase(context: Context): BusinessDatabase {
            return Room.databaseBuilder(
                context,
                BusinessDatabase::class.java,
                BusinessDatabase::class.java.name
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