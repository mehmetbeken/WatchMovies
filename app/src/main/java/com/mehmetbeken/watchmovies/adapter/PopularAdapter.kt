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


class PopularAdapter(val movieListesi: List<ResultItem>) :
    RecyclerView.Adapter<PopularAdapter.MovieViewHolder>() {
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.popular_row, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieListesi[position]
        holder.itemView.apply {


            Glide.with(this).load("https://image.tmdb.org/t/p/w342/${movie.poster_path}")
                .into(popularImage)
        }
        holder.itemView.setOnClickListener {
            val action = MovieListFragmentDirections.actionMovieListToArticleFragment(
                resultItem = ResultItem(
                    movie.id,
                    movie.overview,
                    movie.poster_path,
                    movie.release_date,
                    movie.title,
                    movie.vote_average,
                    movie.backdrop_path
                )
            )

            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return movieListesi.size
    }
}