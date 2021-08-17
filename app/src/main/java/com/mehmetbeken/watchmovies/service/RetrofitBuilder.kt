package com.mehmetbeken.watchmovies.service

import com.mehmetbeken.watchmovies.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: MovieAPI by lazy {
        retrofit.create(MovieAPI::class.java)
    }
}