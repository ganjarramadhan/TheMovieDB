package com.ganjarramadhan.themoviedb.app.dagger.components

import android.content.Context
import com.ganjarramadhan.themoviedb.app.constant.AppsPreferences
import com.ganjarramadhan.themoviedb.app.dagger.module.AppModule
import com.ganjarramadhan.themoviedb.app.dagger.module.NetworkModule
import com.ganjarramadhan.themoviedb.app.dagger.scope.AppScope
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by ganjarramadhan on 10/04/18.
 * ganjar.ramadhan05@gmail.com
 */

@AppScope
@Component(modules = [(AppModule::class), (NetworkModule::class)])

interface AppComponent {

    fun retrofit(): Retrofit

    fun context(): Context

    fun okHttpClient(): OkHttpClient

    fun preference(): AppsPreferences

}
