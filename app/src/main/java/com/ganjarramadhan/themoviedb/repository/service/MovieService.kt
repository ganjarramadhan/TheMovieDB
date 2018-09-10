package com.ganjarramadhan.themoviedb.repository.service

import com.ganjarramadhan.themoviedb.entity.response.Movie
import com.ganjarramadhan.themoviedb.entity.response.MovieListResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by ganjarramadhan on 9/6/18.
 * ganjar.ramadhan05@gmail.com
 */
interface MovieService {

    @GET
    fun getMovies(@Url url: String, @Query("api_key") apiKey: String, @Query("page") page: Int):
            Flowable<MovieListResponse>


    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String):
            Observable<Movie>



}