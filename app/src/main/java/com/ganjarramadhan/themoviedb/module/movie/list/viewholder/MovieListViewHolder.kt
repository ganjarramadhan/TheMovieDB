package com.ganjarramadhan.themoviedb.module.movie.list.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.ganjarramadhan.themoviedb.entity.response.Movie
import com.ganjarramadhan.themoviedb.util.extension.toMovieDbImageUrl
import com.ganjarramadhan.themoviedb.util.glide.GlideApp
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by ganjarramadhan on 9/6/18.
 * ganjar.ramadhan05@gmail.com
 */
class MovieListViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bind(movie: Movie) {
        GlideApp.with(itemView.context).load(movie.posterPath?.toMovieDbImageUrl()).into(itemView.imgPoster)
        itemView.tvTitle.text = movie.title
    }
}