package com.ganjarramadhan.themoviedb.module.movie.list.dagger

import com.ganjarramadhan.themoviedb.app.constant.AppsPreferences
import com.ganjarramadhan.themoviedb.module.movie.list.MovieListFragment
import com.ganjarramadhan.themoviedb.module.movie.list.mvp.MovieListModel
import com.ganjarramadhan.themoviedb.module.movie.list.mvp.MovieListPresenter
import com.ganjarramadhan.themoviedb.module.movie.list.mvp.MovieListView
import com.ganjarramadhan.themoviedb.repository.service.MovieListService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by ganjarramadhan on 9/6/18.
 * ganjar.ramadhan05@gmail.com
 */
@Module
class MovieListModule(private val fragment: MovieListFragment) {

    @MovieListScope
    @Provides
    fun view() = MovieListView(fragment.context!!)

    @MovieListScope
    @Provides
    fun presenter(view: MovieListView, model: MovieListModel) = MovieListPresenter(view, model)

    @MovieListScope
    @Provides
    fun model(appsPreferences: AppsPreferences, service: MovieListService) =
            MovieListModel(fragment, appsPreferences, service)

    @MovieListScope
    @Provides
    fun service(retrofit: Retrofit) = retrofit.create(MovieListService::class.java)

}