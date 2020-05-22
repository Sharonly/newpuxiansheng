package com.puxiansheng.logic.data.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.puxiansheng.logic.bean.User


@Database(entities = [User::class], version = 2, exportSchema = false)
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
                })
                .build()
        }

    }
}
