package com.mehmetbeken.watchmovies.service

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mehmetbeken.watchmovies.model.ResultItem

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(resultItem: ResultItem): Long

    @Query("SELECT*FROM resultItems")
    fun getAllMovies(): LiveData<List<ResultItem>>

    @Delete
    suspend fun deleteMovies(resultItem: ResultItem)
}