package com.mehmetbeken.watchmovies.model

data class ResultItem(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Float,
    val backdrop_path: String
)