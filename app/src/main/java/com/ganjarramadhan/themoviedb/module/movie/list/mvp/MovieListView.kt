package com.ganjarramadhan.themoviedb.module.movie.list.mvp

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.FrameLayout
import com.ganjarramadhan.themoviedb.R
import com.ganjarramadhan.themoviedb.entity.response.Movie
import com.ganjarramadhan.themoviedb.module.movie.list.adapter.MovieListAdapter
import com.ganjarramadhan.themoviedb.util.decoration.GridSpacesItemDecoration
import com.ganjarramadhan.themoviedb.util.extension.toPx
import kotlinx.android.synthetic.main.fragment_movie_list.view.*

/**
 * Created by ganjarramadhan on 9/6/18.
 * ganjar.ramadhan05@gmail.com
 */
class MovieListView(context: Context): FrameLayout(context) {

    private val adapter = MovieListAdapter(context)
    private val layoutManager = GridLayoutManager(context, 2)
    private val decoration = GridSpacesItemDecoration(4.toPx())

    init {
        View.inflate(context, R.layout.fragment_movie_list, this)

        rvMovieList.layoutManager = layoutManager
        rvMovieList.addItemDecoration(decoration)
        rvMovieList.adapter = adapter

    }

    fun addMoviesToList(results: List<Movie>?) {
        if (results != null){
            adapter.insertData(results)
        }
    }
}