package com.mehmetbeken.watchmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetbeken.watchmovies.model.MoviesModel
import com.mehmetbeken.watchmovies.model.ResultItem
import com.mehmetbeken.watchmovies.repository.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    val pageNumber = 1
    val popularMovies = MutableLiveData<MoviesModel>()
    val ratedMovies = MutableLiveData<MoviesModel>()
    val upComingMovies = MutableLiveData<MoviesModel>()
    val searchMovie = MutableLiveData<MoviesModel>()
    val detailMovie = MutableLiveData<ResultItem>()


    fun getMovies(language: String, pageNumber: Int) {
        viewModelScope.launch {
            val response = movieRepository.getPopularMovies(language, pageNumber)
            if (response.isSuccessful) {
                response.body()?.let {
                    popularMovies.postValue(it)
                }
            }
        }
    }

    fun getRatedMovies(language: String, pageNumber: Int) {
        viewModelScope.launch {
            val response = movieRepository.getTopRatedMovies(language, pageNumber)
            if (response.isSuccessful) {
                response.body()?.let {
                    ratedMovies.postValue(it)
                }
            }
        }
    }

    fun getUpComingMovies(language: String, pageNumber: Int) {
        viewModelScope.launch {
            val response = movieRepository.getUpComing(language, pageNumber)
            if (response.isSuccessful) {
                response.body()?.let {
                    upComingMovies.postValue(it)
                }
            }
        }
    }

    fun getSearchMovies(searchQuery: String, language: String) {
        viewModelScope.launch {
            val response = movieRepository.getSearchMovies(searchQuery, language)
            if (response.isSuccessful) {
                response.body()?.let {
                    searchMovie.postValue(it)
                }
            }
        }
    }
}