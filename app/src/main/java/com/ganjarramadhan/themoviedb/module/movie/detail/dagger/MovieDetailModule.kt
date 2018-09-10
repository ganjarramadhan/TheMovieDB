package com.ganjarramadhan.themoviedb.module.movie.detail.dagger

import com.ganjarramadhan.themoviedb.app.constant.AppsPreferences
import com.ganjarramadhan.themoviedb.module.movie.detail.MovieDetailActivity
import com.ganjarramadhan.themoviedb.module.movie.detail.mvp.MovieDetailModel
import com.ganjarramadhan.themoviedb.module.movie.detail.mvp.MovieDetailPresenter
import com.ganjarramadhan.themoviedb.module.movie.detail.mvp.MovieDetailView
import com.ganjarramadhan.themoviedb.repository.service.MovieService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by ganjarramadhan on 9/10/18.
 * ganjar.ramadhan05@gmail.com
 */

@Module
class MovieDetailModule(private val activity: MovieDetailActivity) {

    @MovieDetailScope
    @Provides
    fun view() = MovieDetailView(activity)

    @MovieDetailScope
    @Provides
    fun model(appsPreferences: AppsPreferences, service: MovieService) =
            MovieDetailModel(activity, appsPreferences, service)

    @MovieDetailScope
    @Provides
    fun presenter(view: MovieDetailView, model: MovieDetailModel) = MovieDetailPresenter(view, model)

    @MovieDetailScope
    @Provides
    fun service(retrofit: Retrofit) = retrofit.create(MovieService::class.java)

}