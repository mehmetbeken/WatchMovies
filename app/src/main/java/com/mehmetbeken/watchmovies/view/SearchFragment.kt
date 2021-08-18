package com.mehmetbeken.watchmovies.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.HORIZONTAL
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.mehmetbeken.watchmovies.R
import com.mehmetbeken.watchmovies.adapter.PopularAdapter
import com.mehmetbeken.watchmovies.adapter.SearchAdapter
import com.mehmetbeken.watchmovies.repository.MovieRepository
import com.mehmetbeken.watchmovies.viewmodel.MovieListViewModel
import com.mehmetbeken.watchmovies.viewmodel.MovieListViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {

    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var searchAdapter: SearchAdapter
    val movieRepository = MovieRepository()
    val viewModelFactory = MovieListViewModelFactory(movieRepository)


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
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        movieListViewModel =
            ViewModelProvider(this, viewModelFactory)[MovieListViewModel::class.java]


        var job: Job? = null
        searchMovie.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        movieListViewModel.getSearchMovies(editable.toString(),"tr-TR")

                        searchRecycler.layoutManager =
                            LinearLayoutManager(context, HORIZONTAL, false)
                        movieListViewModel.searchMovie.observe(viewLifecycleOwner, Observer {
                            searchAdapter = SearchAdapter(it.results)
                            searchRecycler.adapter = searchAdapter
                            searchAdapter.notifyDataSetChanged()
                        })
                    }
                }
            }
        }
        //search
        /*

          movieListViewModel.getSearchMovies("", 1)
          searchRecycler.layoutManager = LinearLayoutManager(context, HORIZONTAL,false)


          movieListViewModel.searchMovie.observe(viewLifecycleOwner, Observer {
              searchAdapter = SearchAdapter(it.results)
              recyclerViewPopular.adapter = searchAdapter
              searchAdapter.notifyDataSetChanged()
      })
      */
    }
}