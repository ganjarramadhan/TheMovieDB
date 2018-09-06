package com.ganjarramadhan.themoviedb.module.movie.list.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ganjarramadhan.themoviedb.R
import com.ganjarramadhan.themoviedb.entity.response.Movie
import com.ganjarramadhan.themoviedb.module.movie.list.viewholder.MovieListViewHolder

/**
 * Created by ganjarramadhan on 9/6/18.
 * ganjar.ramadhan05@gmail.com
 */
class MovieListAdapter(private val context: Context) : RecyclerView.Adapter<MovieListViewHolder>() {

    private val data = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return MovieListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun insertData(results: List<Movie>) {
        val position = if (data.size == 0) {
            0
        } else {
            data.size - 1
        }

        data.addAll(results)
        notifyItemRangeInserted(position, results.size)
    }
}