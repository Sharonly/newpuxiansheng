package com.puxiansheng.logic.data.order

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.puxiansheng.logic.bean.Order

@Database(entities = [Order::class], version = 2, exportSchema = false)
abstract class OrderDatabase : RoomDatabase() {
    abstract fun getOrderDao() : OrderDao
    companion object {
        private var orderDatabase: OrderDatabase? = null

        fun getInstance(context : Context) : OrderDatabase {
            return orderDatabase
                ?: createDatabase(
                    context
                )
        }

        private fun createDatabase(context : Context) : OrderDatabase {
            return Room.databaseBuilder(context, OrderDatabase::class.java, OrderDatabase::class.java.name)
//                .fallbackToDestructiveMigration()
                .addMigrations(object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                    }
                })//版本升级为2，解决错误的升级数据库。导致灰度期间出现了不少crash
                .build()
        }
    }
}