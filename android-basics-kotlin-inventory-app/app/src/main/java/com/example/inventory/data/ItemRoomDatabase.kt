package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase: RoomDatabase() {
        abstract fun itemDao(): ItemDao

        companion object {
            //参考：https://zenn.dev/yass97/articles/ac5f7b0320edc8
            @Volatile
            private var INSTANCE: ItemRoomDatabase? = null

            fun getDatabase(context: Context): ItemRoomDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context,
                        ItemRoomDatabase::class.java,
                        "item_database"
                    )
                        /**
                         * 移行は、この Codelab の対象外です。簡単なソリューションは、データベースを破棄して再構築することです。つまりデータは失われます。
                         */
                        .fallbackToDestructiveMigration()
//                        .createFromAsset("database/bus_schedule.db")

//                        .addMigrations(
//                            MIGRATION_1_2_sample, MIGRATION_2_3_sample
//                        )

//                    .addCallback(
//                        object : RoomDatabase.Callback() {
//                            override fun onCreate(db: SupportSQLiteDatabase) {
//                                super.onCreate(db)
//                                val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
//                                    .setInputData(workDataOf(KEY_FILENAME to PLANT_DATA_FILENAME))
//                                    .build()
//                                WorkManager.getInstance(context).enqueue(request)
//                            }
//                        }
//                    )
                        .build()
                    this.INSTANCE = instance

                    instance
                }
            }

        }
    }

//val MIGRATION_1_2_sample = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL(
//            "CREATE TABLE Memo (" +
//                    " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    " name TEXT NOT NULL " +
//                    ");"
//        )
//        database.execSQL("alter table SomeSample add age integer default 10;")
//    }
//}
//
//val MIGRATION_2_3_sample = object : Migration(2, 3) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL("alter table Schedule add sex integer default 0;")
//    }
//}
