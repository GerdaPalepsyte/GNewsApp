package com.example.gnewsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbNews::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
