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
    version = 3,
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
                    },MIGRATION_2_3)
                .build()
        }

        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("alter table table_info ADD  COLUMN _user_id INTEGER NOT NULL DEFAULT 0 ")
                database.execSQL("alter table table_info ADD COLUMN _param TEXT NOT NULL DEFAULT 'null' ")
            }
        }
    }
}