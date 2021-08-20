package com.mehmetbeken.watchmovies.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "resultItems")
data class ResultItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Float,
    val backdrop_path: String

) : Serializable