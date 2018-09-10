package com.ganjarramadhan.themoviedb.module.movie.list.mvp

import com.ganjarramadhan.themoviedb.app.constant.AppConstant
import com.ganjarramadhan.themoviedb.app.constant.AppsPreferences
import com.ganjarramadhan.themoviedb.base.BaseModel
import com.ganjarramadhan.themoviedb.entity.response.MovieListResponse
import com.ganjarramadhan.themoviedb.module.movie.list.MovieListFragment
import com.ganjarramadhan.themoviedb.repository.service.MovieService
import io.reactivex.Flowable

/**
 * Created by ganjarramadhan on 9/6/18.
 * ganjar.ramadhan05@gmail.com
 */
class MovieListModel(private val fragment: MovieListFragment,
                     private val appsPreferences: AppsPreferences,
                     private val service: MovieService) : BaseModel(appsPreferences) {

    fun getListType(): Int {
        return fragment.arguments?.getInt(MovieListFragment.LIST_TYPE) ?: MovieListFragment.LIST_POPULAR
    }

    fun getUrl(): String {
        return when(getListType()){
            MovieListFragment.LIST_POPULAR -> {
                AppConstant.URL_POPULAR_MOVIE
            }
            else -> {
                AppConstant.URL_TOP_RATED_MOVIE
            }
        }
    }

    fun getMovies(page: Int): Flowable<MovieListResponse>{
        return service.getMovies(getUrl(), AppConstant.API_KEY_V3, page)
    }

}