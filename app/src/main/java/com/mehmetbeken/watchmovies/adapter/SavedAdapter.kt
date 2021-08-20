package com.mehmetbeken.watchmovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mehmetbeken.watchmovies.R
import com.mehmetbeken.watchmovies.model.ResultItem
import kotlinx.android.synthetic.main.saved_row.view.*

class SavedAdapter :
    RecyclerView.Adapter<SavedAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<ResultItem>() {
        override fun areItemsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.saved_row, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieSaved = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load("https://image.tmdb.org/t/p/w342/${movieSaved.poster_path}")
                .into(saved_image)
            saved_title.text = movieSaved.title
            saved_overview.text = movieSaved.overview
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}

