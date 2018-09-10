package com.ganjarramadhan.themoviedb.module.movie.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ganjarramadhan.themoviedb.app.TheMovieDbApp
import com.ganjarramadhan.themoviedb.base.BaseActivity
import com.ganjarramadhan.themoviedb.module.movie.detail.dagger.DaggerMovieDetailComponent
import com.ganjarramadhan.themoviedb.module.movie.detail.dagger.MovieDetailModule
import com.ganjarramadhan.themoviedb.module.movie.detail.mvp.MovieDetailPresenter
import com.ganjarramadhan.themoviedb.module.movie.detail.mvp.MovieDetailView
import javax.inject.Inject

/**
 * Created by ganjarramadhan on 9/4/18.
 * ganjar.ramadhan05@gmail.com
 */
class MovieDetailActivity : BaseActivity<MovieDetailPresenter>() {

    @Inject
    lateinit var view: MovieDetailView

    override fun beforeViewCreated() {
        DaggerMovieDetailComponent.builder()
                .appComponent(TheMovieDbApp[this].appComponent)
                .movieDetailModule(MovieDetailModule(this))
                .build().inject(this)
    }

    override fun setView(): View {
        return view
    }

    override fun afterViewCreated(savedInstanceState: Bundle?) {
    }

    companion object {
        const val KEY_MOVIE_ID = "movieId"

        fun start(context: Context, movieId: Int) {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(KEY_MOVIE_ID, movieId)
            context.startActivity(intent)
        }
    }
}