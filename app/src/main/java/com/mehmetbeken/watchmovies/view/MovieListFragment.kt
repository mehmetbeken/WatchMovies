package com.mehmetbeken.watchmovies.view


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper.*


import com.mehmetbeken.watchmovies.R
import com.mehmetbeken.watchmovies.WatchMovies
import com.mehmetbeken.watchmovies.adapter.PopularAdapter
import com.mehmetbeken.watchmovies.adapter.RatedAdapter
import com.mehmetbeken.watchmovies.adapter.UpComingAdapter
import com.mehmetbeken.watchmovies.repository.MovieRepository
import com.mehmetbeken.watchmovies.viewmodel.MovieListViewModel
import com.mehmetbeken.watchmovies.viewmodel.MovieListViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie_list.*


class MovieListFragment : Fragment() {


    private lateinit var popularAdapter: PopularAdapter
    private lateinit var ratedAdapter: RatedAdapter
    private lateinit var upComingAdapter: UpComingAdapter
    lateinit var viewModel: MovieListViewModel

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
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=(activity as WatchMovies).viewModel


        //popüler filmler
        viewModel.getMovies("tr-TR", 1)
        recyclerViewPopular.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)

        viewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            popularAdapter = PopularAdapter(it.results)
            recyclerViewPopular.adapter = popularAdapter
            popularAdapter.notifyDataSetChanged()

        })
        //en cok oy alan filmler
        viewModel.getRatedMovies("tr-TR", 1)
        recyclerViewRated.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)

        viewModel.ratedMovies.observe(viewLifecycleOwner, Observer {
            ratedAdapter = RatedAdapter(it.results)
            recyclerViewRated.adapter = ratedAdapter
            ratedAdapter.notifyDataSetChanged()
        })
        //Çıkacak Filmler
        viewModel.getUpComingMovies("tr-TR", 1)
        recyclerViewUpComing.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)

        viewModel.upComingMovies.observe(viewLifecycleOwner, Observer {
            upComingAdapter = UpComingAdapter(it.results)
            recyclerViewUpComing.adapter = upComingAdapter
            upComingAdapter.notifyDataSetChanged()
        })
    }
}