package com.ganjarramadhan.themoviedb.module.movie.list.mvp

import android.content.Context
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.ganjarramadhan.themoviedb.R
import com.ganjarramadhan.themoviedb.entity.response.Movie
import com.ganjarramadhan.themoviedb.module.movie.detail.MovieDetailActivity
import com.ganjarramadhan.themoviedb.module.movie.list.adapter.MovieListAdapter
import com.ganjarramadhan.themoviedb.util.decoration.GridSpacesItemDecoration
import com.ganjarramadhan.themoviedb.util.extension.toPx
import com.ganjarramadhan.themoviedb.util.recyclerview.InfiniteAdapterPlus
import io.reactivex.processors.PublishProcessor
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_movie_list.view.*

/**
 * Created by ganjarramadhan on 9/6/18.
 * ganjar.ramadhan05@gmail.com
 */
class MovieListView(context: Context): FrameLayout(context) {

    private val adapter = MovieListAdapter(context)
    private val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    private val decoration = GridSpacesItemDecoration(4.toPx())

    init {
        View.inflate(context, R.layout.fragment_movie_list, this)

        rvMovieList.setHasFixedSize(true)
        rvMovieList.addItemDecoration(decoration)
        rvMovieList.layoutManager = layoutManager
        rvMovieList.adapter = adapter

    }

    fun addMoviesToList(results: List<Movie>?, loadMore: Boolean) {
        if (swipeRefresh.isRefreshing) swipeRefresh.isRefreshing = false
        if (results != null){
            adapter.insertData(results)
            adapter.setShouldLoadMore(loadMore)
        }
    }

    fun observeLoadMoreMovies(pageManager: PublishProcessor<Boolean>) {

        swipeRefresh.setOnRefreshListener {
            pageManager.onNext(true)
        }

        adapter.loadMoreListener = object : InfiniteAdapterPlus.Listener {
            override fun onLoadMore() {
                pageManager.onNext(false)
            }
        }

        adapter.errorListener = object : InfiniteAdapterPlus.OnErrorListener {
            override fun onRetryLoadMore() {
                adapter.showError(false)
                pageManager.onNext(false)
            }
        }

        swipeRefresh.isRefreshing = true
        pageManager.onNext(true)
    }

    fun observeMovieItemClick(): PublishSubject<Movie> {
        return adapter.publishMovieClick
    }

    fun clearAllMovies() {
        adapter.removeAll()
    }

    fun showMovieDetail(movie: Movie) {
        movie.id?.let { it ->
            MovieDetailActivity.start(context, it)
        }
    }

    fun showError(errorMessage: String?) {
        if (swipeRefresh.isRefreshing) swipeRefresh.isRefreshing = false
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }
}