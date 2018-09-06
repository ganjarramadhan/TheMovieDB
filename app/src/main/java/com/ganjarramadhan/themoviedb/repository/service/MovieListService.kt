package com.ganjarramadhan.themoviedb.repository.service

import com.ganjarramadhan.themoviedb.entity.response.MovieListResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by ganjarramadhan on 9/6/18.
 * ganjar.ramadhan05@gmail.com
 */
interface MovieListService {

    @GET
    fun getMovies(@Url url: String, @Query("api_key") apiKey: String, @Query("page") page: Int):
            Flowable<MovieListResponse>


}