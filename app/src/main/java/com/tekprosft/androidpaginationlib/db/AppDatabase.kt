package com.tekprosft.androidpaginationlib.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tekprosft.androidpaginationlib.model.Cheese

@Database(entities = [Cheese::class],version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cheeseDao() : CheeseDao

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null
        private const val DATABASE_NAME = "PlainNote.db"

        fun getDatabase(context: Context) : AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context,
                    AppDatabase::class.java, DATABASE_NAME).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}