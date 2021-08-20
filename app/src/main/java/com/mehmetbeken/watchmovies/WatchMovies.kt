package com.mehmetbeken.watchmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mehmetbeken.watchmovies.repository.MovieRepository
import com.mehmetbeken.watchmovies.service.MoviesDatabase
import com.mehmetbeken.watchmovies.viewmodel.MovieListViewModel
import com.mehmetbeken.watchmovies.viewmodel.MovieListViewModelFactory
import kotlinx.android.synthetic.main.activity_watchmovies.*


class WatchMovies : AppCompatActivity() {

    lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchmovies)

        val movieRepository = MovieRepository(MoviesDatabase(this))
        val viewModelProviderFactory = MovieListViewModelFactory(movieRepository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MovieListViewModel::class.java)
        bottomNavigationView.setupWithNavController(fragment.findNavController())
    }
}