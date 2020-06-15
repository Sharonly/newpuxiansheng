package com.puxiansheng.logic.data.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.puxiansheng.logic.bean.User


@Database(entities = [User::class], version = 3, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var userDatabase: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            return userDatabase
                ?: createDatabase(
                    context
                )
        }

        private fun createDatabase(context: Context): UserDatabase {
            return Room.databaseBuilder(
                context,
                UserDatabase::class.java,
                UserDatabase::class.java.name
            )
//                .fallbackToDestructiveMigration()
                .addMigrations(object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                    }
                },MIGRATION_2_3)
                .build()
        }



        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table user_table ADD  COLUMN _city_path_id TEXT NOT NULL DEFAULT 'null' ")
                database.execSQL("alter table user_table ADD  COLUMN _city_id INTEGER NOT NULL DEFAULT 0")
                database.execSQL("alter table user_table ADD  COLUMN _tips_msg TEXT NOT NULL DEFAULT 'null' ")
                database.execSQL("alter table user_table ADD  COLUMN _name TEXT NOT NULL DEFAULT 'null'")
                database.execSQL("alter table user_table ADD  COLUMN _user_sex INTEGER NOT NULL DEFAULT 0")
                database.execSQL("alter table user_table ADD  COLUMN _view_city_name TEXT NOT NULL DEFAULT 'null' ")
                database.execSQL("alter table user_table ADD  COLUMN _view_city_path TEXT NOT NULL DEFAULT 'null' ")

            }
        }

    }
}
