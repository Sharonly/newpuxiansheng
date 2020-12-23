package com.puxiansheng.logic.data.device

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.puxiansheng.logic.bean.Device

@Database(entities = [Device::class], version = 3, exportSchema = false)
abstract class DeviceDatabase : RoomDatabase() {

    abstract fun deviceDao(): DeviceDao

    companion object {
        private var deviceDatabase: DeviceDatabase? = null

        fun getInstance(context: Context): DeviceDatabase {
            return deviceDatabase
                ?: createDatabase(
                    context
                )
        }

        private fun createDatabase(context: Context): DeviceDatabase {
            return Room.databaseBuilder(
                context,
                DeviceDatabase::class.java,
                DeviceDatabase::class.java.name
            )
//                .fallbackToDestructiveMigration()
                .addMigrations(object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                    }
                },MIGRATION_2_3)//版本升级为2，解决错误的升级数据库。导致灰度期间出现了不少crash
                .build()
        }

        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }

    }
}