package com.ganjarramadhan.themoviedb.app

import android.app.Activity
import android.app.Application
import android.app.Service
import android.support.v4.app.FragmentActivity
import com.ganjarramadhan.themoviedb.BuildConfig.DEBUG
import com.ganjarramadhan.themoviedb.app.constant.AppConstant
import com.ganjarramadhan.themoviedb.app.dagger.components.AppComponent
import com.ganjarramadhan.themoviedb.app.dagger.components.DaggerAppComponent
import com.ganjarramadhan.themoviedb.app.dagger.module.AppModule
import com.ganjarramadhan.themoviedb.app.dagger.module.NetworkModule
import timber.log.Timber

/**
 * Created by ganjarramadhan on 9/4/18.
 * ganjar.ramadhan05@gmail.com
 */
class TheMovieDbApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        // init timber for logging
        if (DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    super.log(priority, AppConstant.LOG_TAG, message, t)
                }
            })
        }

        // init app component
        val appModule = AppModule(this)
        val networkModule = NetworkModule(appModule.preferences())
        appComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .networkModule(networkModule)
                .build()

    }

    companion object {

        operator fun get(activity: Activity): TheMovieDbApp {
            return activity.application as TheMovieDbApp
        }

        operator fun get(activity: FragmentActivity): TheMovieDbApp {
            return activity.application as TheMovieDbApp
        }

        operator fun get(service: Service): TheMovieDbApp {
            return service.application as TheMovieDbApp
        }
    }
}