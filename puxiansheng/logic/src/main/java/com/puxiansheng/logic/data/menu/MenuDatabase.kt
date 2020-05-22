package com.puxiansheng.logic.data.menu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.puxiansheng.logic.bean.MenuItem

@Database(entities = [MenuItem::class], version = 2, exportSchema = false)
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
                .build()
        }
    }
}