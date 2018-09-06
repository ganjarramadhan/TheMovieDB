package com.ganjarramadhan.themoviedb.module.movie.list.dagger

import com.ganjarramadhan.themoviedb.app.dagger.components.AppComponent
import com.ganjarramadhan.themoviedb.module.movie.list.MovieListFragment
import dagger.Component

/**
 * Created by ganjarramadhan on 9/6/18.
 * ganjar.ramadhan05@gmail.com
 */
@MovieListScope
@Component(modules = [MovieListModule::class], dependencies = [AppComponent::class])
interface MovieListComponent {
    fun inject(fragment: MovieListFragment)
}