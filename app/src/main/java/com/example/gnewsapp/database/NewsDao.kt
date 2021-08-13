package com.example.gnewsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable
import java.util.*

@Dao
interface NewsDao {
    @Query("SELECT * FROM dbnews")
    fun observeNews(): Observable<List<DbNews>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<DbNews>)
}
