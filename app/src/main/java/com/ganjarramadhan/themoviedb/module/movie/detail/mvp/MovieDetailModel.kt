package com.ganjarramadhan.themoviedb.module.movie.detail.mvp

import com.ganjarramadhan.themoviedb.app.constant.AppConstant
import com.ganjarramadhan.themoviedb.app.constant.AppsPreferences
import com.ganjarramadhan.themoviedb.base.BaseModel
import com.ganjarramadhan.themoviedb.entity.response.Movie
import com.ganjarramadhan.themoviedb.module.movie.detail.MovieDetailActivity
import com.ganjarramadhan.themoviedb.repository.service.MovieService
import io.reactivex.Observable

/**
 * Created by ganjarramadhan on 9/10/18.
 * ganjar.ramadhan05@gmail.com
 */
class MovieDetailModel(private val activity: MovieDetailActivity,
                       private val appsPreferences: AppsPreferences,
                       private val service: MovieService) : BaseModel(appsPreferences) {


    fun getMovieId(): Int {
        return activity.intent.getIntExtra(MovieDetailActivity.KEY_MOVIE_ID, 0)
    }

    fun loadMovieDetail(): Observable<Movie> {
        val movieId = getMovieId()
        return service.getMovieDetail(movieId, AppConstant.API_KEY_V3)
    }

}