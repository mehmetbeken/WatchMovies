package com.mehmetbeken.watchmovies.service


import com.mehmetbeken.watchmovies.model.MoviesModel
import com.mehmetbeken.watchmovies.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    //Search   /3/search/movie?api_key=c41ea5402a64eba56b4119a2e1366bae&query=Jack+Reacher
    @GET("/3/search/movie")
    suspend fun getSearchMovies(
        @Query("query")
        searchQuery: String,
        @Query("language")
        language: String,
        @Query("api_key")
        api_key: String? = API_KEY
    ): Response<MoviesModel>

    //Popular Movie /3/movie/popular?api_key=c41ea5402a64eba56b4119a2e1366bae&language=tr-TR&page=1
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("language")
        language: String,
        @Query("page")
        pageNumber: Int? = 1,
        @Query("api_key")
        api_key: String? = API_KEY
    ): Response<MoviesModel>

    //Top-Rated /3/movie/top_rated?api_key=c41ea5402a64eba56b4119a2e1366bae&language=tr-TR&page=1
    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language")
        language: String,
        @Query("page")
        pageNumber: Int? = 1,
        @Query("api_key")
        api_key: String? = API_KEY
    ): Response<MoviesModel>

    //Upcoming /3/movie/upcoming?api_key=c41ea5402a64eba56b4119a2e1366bae&language=tr-TR&page=1
    @GET("/3/movie/upcoming")
    suspend fun getUpComing(
        @Query("language")
        language: String,
        @Query("page")
        pageNumber: Int? = 1,
        @Query("api_key")
        api_key: String? = API_KEY
    ): Response<MoviesModel>
}