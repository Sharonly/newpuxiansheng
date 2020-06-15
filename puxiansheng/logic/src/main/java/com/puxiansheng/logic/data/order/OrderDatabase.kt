package com.puxiansheng.logic.data.order

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.puxiansheng.logic.bean.Order

@Database(entities = [Order::class], version = 4, exportSchema = false)
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
                .addMigrations(MIGRATION_1_3)
                .addMigrations(MIGRATION_2_3)
                .addMigrations(MIGRATION_3_4)
                .build()
        }

        val  MIGRATION_1_3: Migration = object : Migration(1, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table table_orders ADD  COLUMN _is_success INTEGER ")
                database.execSQL("alter table table_orders ADD  COLUMN _allfacilities TEXT ")
                database.execSQL("alter table table_orders ADD  COLUMN _lng TEXT ")
                database.execSQL("alter table table_orders ADD  COLUMN _lat TEXT ")
            }
        }

        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("alter table table_orders ADD  COLUMN _is_success INTEGER")
//                database.execSQL("alter table table_orders ADD  COLUMN _allfacilities TEXT ")
//                database.execSQL("alter table table_orders ADD  COLUMN _lng TEXT ")
//                database.execSQL("alter table table_orders ADD  COLUMN _lat TEXT ")
            }
        }

        val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table table_orders ADD  COLUMN _rent_view TEXT NOT NULL")
                database.execSQL("alter table table_orders ADD  COLUMN _view_opening TEXT NOT NULL")
                database.execSQL("alter table table_orders ADD  COLUMN _view_can_empty TEXT NOT NULL")
                database.execSQL("alter table table_orders ADD  COLUMN _is_top INTEGER NOT NULL")
                database.execSQL("alter table table_orders ADD  COLUMN _is_hot INTEGER NOT NULL")
                database.execSQL("alter table table_orders ADD  COLUMN _is_recommend INTEGER NOT NULL")
                database.execSQL("alter table table_orders ADD  COLUMN _is_large_order INTEGER NOT NULL")
                database.execSQL("alter table table_orders ADD  COLUMN _large_order_img TEXT NOT NULL")
                database.execSQL("alter table table_orders ADD  COLUMN _category_acreage TEXT NOT NULL")
                database.execSQL("alter table table_orders ADD  COLUMN _formatted_area TEXT NOT NULL")
                database.execSQL("alter table table_orders ADD  COLUMN _is_vip INTEGER NOT NULL")
                database.execSQL("alter table table_orders ADD  COLUMN _data_type TEXT NOT NULL")
                database.execSQL("alter table table_orders ADD  COLUMN _jump_type INTEGER NOT NULL")
                database.execSQL("alter table table_orders ADD  COLUMN _jump_view TEXT NOT NULL")
                database.execSQL("alter table table_orders ADD  COLUMN _jump_param TEXT NOT NULL")
            }
        }


    }
}