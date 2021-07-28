package com.walmartsample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.walmartsample.data.GenreConverters
import com.walmartsample.model.Movie

@Database(entities = [Movie::class], version = 1)
@TypeConverters(GenreConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}