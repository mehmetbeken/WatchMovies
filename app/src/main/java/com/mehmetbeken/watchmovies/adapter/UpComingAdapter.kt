package com.mehmetbeken.watchmovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mehmetbeken.watchmovies.R
import com.mehmetbeken.watchmovies.model.ResultItem
import kotlinx.android.synthetic.main.rated_row.view.*
import kotlinx.android.synthetic.main.upcoming_row.view.*

class UpComingAdapter(val upComingList: List<ResultItem>) :
    RecyclerView.Adapter<UpComingAdapter.MovieViewHolder>() {
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.upcoming_row, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieUpComing = upComingList[position]
        holder.itemView.apply {
            Glide.with(this).load("https://image.tmdb.org/t/p/w342/${movieUpComing.poster_path}")
                .into(upComingImage)
        }
    }

    override fun getItemCount(): Int {
        return upComingList.size
    }
}