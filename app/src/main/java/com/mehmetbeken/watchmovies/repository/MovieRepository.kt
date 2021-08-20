package com.mehmetbeken.watchmovies.repository

import com.mehmetbeken.watchmovies.model.MoviesModel
import com.mehmetbeken.watchmovies.model.ResultItem
import com.mehmetbeken.watchmovies.service.MoviesDatabase
import com.mehmetbeken.watchmovies.service.RetrofitBuilder
import com.mehmetbeken.watchmovies.util.Constants.Companion.BASE_URL
import retrofit2.Response

class MovieRepository(
    val db: MoviesDatabase
) {
    suspend fun getSearchMovies(searchQuery: String, language: String): Response<MoviesModel> =
        RetrofitBuilder.api.getSearchMovies(searchQuery, language)

    suspend fun getPopularMovies(language: String, pageNumber: Int): Response<MoviesModel> =
        RetrofitBuilder.api.getPopularMovies(language, pageNumber)

    suspend fun getTopRatedMovies(language: String, pageNumber: Int): Response<MoviesModel> =
        RetrofitBuilder.api.getTopRatedMovies(language, pageNumber)

    suspend fun getUpComing(language: String, pageNumber: Int): Response<MoviesModel> =
        RetrofitBuilder.api.getUpComing(language, pageNumber)

    suspend fun upsert(resultItem: ResultItem) = db.getMoviesDao().upsert(resultItem)

    fun getSavedMovies() = db.getMoviesDao().getAllMovies()

    suspend fun deleteMovies(resultItem: ResultItem) = db.getMoviesDao().deleteMovies(resultItem)

}