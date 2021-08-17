package com.mehmetbeken.watchmovies.model

data class MoviesModel(
    val page: Int,
    val results: List<ResultItem>,
    val total_pages: Int,
    val total_results: Int
)