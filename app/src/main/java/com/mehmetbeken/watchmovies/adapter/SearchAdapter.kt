package com.mehmetbeken.watchmovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mehmetbeken.watchmovies.R
import com.mehmetbeken.watchmovies.model.ResultItem
import kotlinx.android.synthetic.main.popular_row.view.*
import kotlinx.android.synthetic.main.search_row.view.*

class SearchAdapter(val searchList: List<ResultItem>) :
    RecyclerView.Adapter<SearchAdapter.MovieViewHolder>() {
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_row, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieSearch = searchList[position]
        holder.itemView.apply {

            Glide.with(this).load("https://image.tmdb.org/t/p/w342/${movieSearch.poster_path}")
                .into(searchImage)
            titleText.text = movieSearch.title
            overviewText.text = movieSearch.overview
            releaseDateText.text = movieSearch.release_date
        }

    }

    override fun getItemCount(): Int {
        return searchList.size
    }
}