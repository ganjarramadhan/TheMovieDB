package com.ganjarramadhan.themoviedb.app.dagger.module

import android.content.SharedPreferences
import com.fatboyindustrial.gsonjodatime.DateTimeConverter
import com.ganjarramadhan.themoviedb.app.constant.AppConstant
import com.ganjarramadhan.themoviedb.app.constant.AppsPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ganjarramadhan.themoviedb.app.dagger.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.joda.time.DateTime
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Created by ganjarramadhan on 10/04/18.
 * ganjar.ramadhan05@gmail.com
 */
@Module
class NetworkModule(private val preferences: SharedPreferences) {

    @Provides
    @AppScope
    fun retrofit(mClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().baseUrl(AppConstant.BASE_URL)
                .addCallAdapterFactory(callAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(mClient)
                .build()
    }

    @Provides
    @AppScope
    fun callAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @AppScope
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(DateTime::class.java, DateTimeConverter())
        return gsonBuilder.create()
    }

    @Provides
    @AppScope
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @AppScope
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message ->
            Timber.tag(AppConstant.LOG_TAG).d(message)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @AppScope
    fun appsPreference(): AppsPreferences = AppsPreferences(preferences)

}
