package com.example.letspractice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.letspractice.entity.StackFlowResponse
import com.example.letspractice.database.dao.StackFlowModelDao

@Database(entities = [StackFlowResponse::class], version = 1, exportSchema = false)
@TypeConverters(com.example.letspractice.utils.TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun StackFlowModelDao(): StackFlowModelDao

    companion object {
        fun getAppDatabase(context: Context): AppDatabase {
            val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "stackoverflow_db")
                    .allowMainThreadQueries()
                    .build()
            return instance
        }
    }


}