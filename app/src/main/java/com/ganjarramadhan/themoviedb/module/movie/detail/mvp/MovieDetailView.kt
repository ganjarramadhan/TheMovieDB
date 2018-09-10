package com.ganjarramadhan.themoviedb.module.movie.detail.mvp

import android.view.View
import android.widget.FrameLayout
import com.ganjarramadhan.themoviedb.R
import com.ganjarramadhan.themoviedb.entity.response.Movie
import com.ganjarramadhan.themoviedb.module.movie.detail.MovieDetailActivity
import com.ganjarramadhan.themoviedb.util.extension.toGenreString
import com.ganjarramadhan.themoviedb.util.extension.toMovieDbImageUrl
import com.ganjarramadhan.themoviedb.util.glide.GlideApp
import kotlinx.android.synthetic.main.activity_movie_detail.view.*

/**
 * Created by ganjarramadhan on 9/10/18.
 * ganjar.ramadhan05@gmail.com
 */
class MovieDetailView(private val activity: MovieDetailActivity) : FrameLayout(activity) {

    init {
        View.inflate(context, R.layout.activity_movie_detail, this)
        stateMovieDetail.showProgress()
    }

    fun loadMovieToView(data: Movie) {
        GlideApp.with(activity).load(data.backdropPath?.toMovieDbImageUrl()).into(imgBackdrop)
        GlideApp.with(activity).load(data.posterPath?.toMovieDbImageUrl()).into(imgPoster)
        tvTitle.text = data.title
        tvRating.text = "${data.voteAverage} / 10"
        tvDuration.text = "${data.runtime} min"
        tvLanguage.text = data.originalLanguage
        tvReleaseDate.text = data.releaseDate
        tvGenre.text = data.genres?.toGenreString()
        toolbar.title = data.title
        tvOverview.text = data.overview
        stateMovieDetail.showContent()
    }

}