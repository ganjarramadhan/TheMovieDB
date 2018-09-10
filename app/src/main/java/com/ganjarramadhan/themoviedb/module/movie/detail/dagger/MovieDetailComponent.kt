package com.ganjarramadhan.themoviedb.module.movie.detail.dagger

import com.ganjarramadhan.themoviedb.app.dagger.components.AppComponent
import com.ganjarramadhan.themoviedb.module.movie.detail.MovieDetailActivity
import dagger.Component

/**
 * Created by ganjarramadhan on 9/10/18.
 * ganjar.ramadhan05@gmail.com
 */
@MovieDetailScope
@Component(modules = [MovieDetailModule::class], dependencies = [AppComponent::class])
interface MovieDetailComponent {
    fun inject(activity: MovieDetailActivity)
}