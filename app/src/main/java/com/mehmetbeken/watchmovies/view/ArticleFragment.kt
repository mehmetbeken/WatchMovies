package com.mehmetbeken.watchmovies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.mehmetbeken.watchmovies.R
import com.mehmetbeken.watchmovies.WatchMovies
import com.mehmetbeken.watchmovies.model.ResultItem
import com.mehmetbeken.watchmovies.repository.MovieRepository
import com.mehmetbeken.watchmovies.viewmodel.MovieListViewModel
import com.mehmetbeken.watchmovies.viewmodel.MovieListViewModelFactory
import kotlinx.android.synthetic.main.fragment_article.*


class ArticleFragment : Fragment() {
    lateinit var viewModel: MovieListViewModel
    val args: ArticleFragmentArgs by navArgs()


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
        viewModel = (activity as WatchMovies).viewModel
        val resultItem = args.resultItem


        observeLiveData()

        saveImage.setOnClickListener {
            viewModel.saveMovie(resultItem)
            Snackbar.make(view, "Movies saved successfully", Snackbar.LENGTH_SHORT).show()
        }

    }

    fun observeLiveData() {
        val resultItem = args.resultItem
        movie_title.text = resultItem.title
        movie_overview.text = resultItem.overview
        movie_release_date.text = resultItem.release_date
        movie_rating.rating = resultItem.vote_average / 2


        Glide.with(this).load("https://image.tmdb.org/t/p/w342/${resultItem.poster_path}")
            .into(movie_poster)
        Glide.with(this).load("https://image.tmdb.org/t/p/w342/${resultItem.backdrop_path}")
            .into(movie_backdrop)


    }


}