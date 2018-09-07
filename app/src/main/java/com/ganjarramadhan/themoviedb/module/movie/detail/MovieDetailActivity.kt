package com.ganjarramadhan.themoviedb.module.movie.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ganjarramadhan.themoviedb.R

/**
 * Created by ganjarramadhan on 9/4/18.
 * ganjar.ramadhan05@gmail.com
 */
class MovieDetailActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
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