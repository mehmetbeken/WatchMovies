package com.mehmetbeken.watchmovies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mehmetbeken.watchmovies.R
import com.mehmetbeken.watchmovies.WatchMovies
import com.mehmetbeken.watchmovies.adapter.SavedAdapter
import com.mehmetbeken.watchmovies.model.ResultItem
import com.mehmetbeken.watchmovies.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_saved_movies.*


class SavedMoviesFragment : Fragment() {

    private lateinit var savedAdapter: SavedAdapter
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
        return inflater.inflate(R.layout.fragment_saved_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as WatchMovies).viewModel

        setupRecyclerView()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val resultItem = savedAdapter.differ.currentList[position]
                viewModel.deleteMovies(resultItem)
                Snackbar.make(view, "Successfully deleted movies", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveMovie(resultItem)
                    }
                    show()
                }
            }

        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(recyclerViewSaved)
        }



        viewModel.getSaveMovies().observe(viewLifecycleOwner, Observer { resultItem ->
            savedAdapter.differ.submitList(resultItem)

        })

    }

    private fun setupRecyclerView() {
        savedAdapter = SavedAdapter()
        recyclerViewSaved.apply {
            adapter = savedAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

}