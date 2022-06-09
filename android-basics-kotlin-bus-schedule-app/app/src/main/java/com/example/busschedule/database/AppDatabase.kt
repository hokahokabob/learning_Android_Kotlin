package com.example.busschedule.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao

/**
 * データベース クラスを使用すると、他のクラスが DAO クラスに簡単にアクセスできます
 */
@Database(entities = arrayOf(Schedule::class), version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        //参考：https://zenn.dev/yass97/articles/ac5f7b0320edc8
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .createFromAsset("database/bus_schedule.db")
                    .build()
                this.INSTANCE = instance

                instance
            }
        }

    }
}