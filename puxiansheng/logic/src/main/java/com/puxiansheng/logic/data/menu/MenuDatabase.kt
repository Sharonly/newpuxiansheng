package com.puxiansheng.logic.data.menu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.puxiansheng.logic.bean.MenuItem

@Database(entities = [MenuItem::class], version = 3, exportSchema = false)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao

    companion object {
        private var menuDatabase: MenuDatabase? = null

        fun getInstance(context: Context): MenuDatabase {
            return menuDatabase
                ?: createDatabase(
                    context
                )
        }

        private fun createDatabase(context: Context): MenuDatabase {
            return Room.databaseBuilder(context, MenuDatabase::class.java, MenuDatabase::class.java.name)
//                .fallbackToDestructiveMigration()
                .addMigrations(object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                    }
                })//版本升级为2，解决错误的升级数据库。导致灰度期间出现了不少crash
                .addMigrations(MIGRATION_2_3)
                .build()
        }

        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table table_menu ADD  COLUMN _color TEXT  NOT NULL DEFAULT 'null'")
                database.execSQL("alter table table_menu ADD  COLUMN _bt_text TEXT  NOT NULL DEFAULT 'null'")
                database.execSQL("alter table table_menu ADD  COLUMN _jump_type INTEGER NOT NULL DEFAULT 0")
                database.execSQL("alter table table_menu ADD  COLUMN _jump_view TEXT  NOT NULL DEFAULT 'null'")
                database.execSQL("alter table table_menu ADD  COLUMN _jump_param TEXT  NOT NULL DEFAULT 'null' ")
            }
        }
    }
}