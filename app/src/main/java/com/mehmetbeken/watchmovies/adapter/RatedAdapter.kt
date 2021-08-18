package com.mehmetbeken.watchmovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mehmetbeken.watchmovies.R
import com.mehmetbeken.watchmovies.model.ResultItem
import com.mehmetbeken.watchmovies.view.MovieListFragmentDirections
import kotlinx.android.synthetic.main.popular_row.view.*
import kotlinx.android.synthetic.main.rated_row.view.*

class RatedAdapter(val ratedList: List<ResultItem>) :
    RecyclerView.Adapter<RatedAdapter.MovieViewHolder>() {
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rated_row, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieRated = ratedList[position]
        holder.itemView.apply {
            Glide.with(this).load("https://image.tmdb.org/t/p/w342/${movieRated.poster_path}")
                .into(ratedImage)
        }
        holder.itemView.setOnClickListener {
            val action = MovieListFragmentDirections.actionMovieListToArticleFragment(
                movieRated.id,
                movieRated.overview,
                movieRated.poster_path,
                movieRated.release_date,
                movieRated.title,
                movieRated.vote_average,
                movieRated.backdrop_path
            )
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return ratedList.size
    }
}