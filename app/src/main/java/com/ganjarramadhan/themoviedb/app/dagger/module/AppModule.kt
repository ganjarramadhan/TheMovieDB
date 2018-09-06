package com.ganjarramadhan.themoviedb.app.dagger.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ganjarramadhan.themoviedb.app.constant.AppConstant
import com.ganjarramadhan.themoviedb.app.dagger.scope.AppScope
import dagger.Module
import dagger.Provides

/**
 * Created by ganjarramadhan on 10/04/18.
 * ganjar.ramadhan05@gmail.com
 */
@Module
class AppModule(private val application: Application) {

    private val context: Context = application.applicationContext

    @AppScope
    @Provides
    fun context(): Context = context

    @AppScope
    @Provides
    fun preferences(): SharedPreferences =
            context.getSharedPreferences(AppConstant.APP_PREFERENCES, Context.MODE_PRIVATE)

}
