package com.walmartsample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.walmartsample.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}