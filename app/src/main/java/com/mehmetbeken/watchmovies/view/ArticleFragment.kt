package com.mehmetbeken.watchmovies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mehmetbeken.watchmovies.R
import com.mehmetbeken.watchmovies.repository.MovieRepository
import com.mehmetbeken.watchmovies.viewmodel.MovieListViewModel
import com.mehmetbeken.watchmovies.viewmodel.MovieListViewModelFactory
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.android.synthetic.main.popular_row.view.*


class ArticleFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel
    private var movieId = 0
    val movieRepository = MovieRepository()
    val viewModelFactory = MovieListViewModelFactory(movieRepository)

    private var _movieId = 0
    private var _movieOverview = ""
    private var _moviePoster = ""
    private var _movieDate = ""
    private var _movieTitle = ""
    private var _movieVote = ""
    private var _movieBackDrop = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            _movieId = ArticleFragmentArgs.fromBundle(it).movieId
            _movieOverview = ArticleFragmentArgs.fromBundle(it).movieOverview
            _moviePoster = ArticleFragmentArgs.fromBundle(it).moviePoster
            _movieDate = ArticleFragmentArgs.fromBundle(it).movieDate
            _movieTitle = ArticleFragmentArgs.fromBundle(it).movieTitle
            _movieBackDrop = ArticleFragmentArgs.fromBundle(it).movieBackDrop
        }
        observeLiveData()
    }

    fun observeLiveData() {
            movie_title.text=_movieTitle
            movie_overview.text=_movieOverview
            movie_release_date.text=_movieDate

        Glide.with(this).load("https://image.tmdb.org/t/p/w342/${_moviePoster}")
            .into(movie_poster)
        Glide.with(this).load("https://image.tmdb.org/t/p/w342/${_movieBackDrop}")
            .into(movie_backdrop)

    }
}