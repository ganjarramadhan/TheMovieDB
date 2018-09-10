package com.ganjarramadhan.themoviedb.module.movie.detail.mvp

import com.ganjarramadhan.themoviedb.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by ganjarramadhan on 9/10/18.
 * ganjar.ramadhan05@gmail.com
 */
class MovieDetailPresenter(private val view: MovieDetailView,
                           private val model: MovieDetailModel) : BasePresenter() {

    override fun onCreate() {
        mCompositeDisposable.add(subscribeLoadMovieDetail())
    }

    private fun subscribeLoadMovieDetail(): Disposable {
        return model.loadMovieDetail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    Timber.d("Success get movie detail")
                    view.loadMovieToView(data)
                }, { error ->
                    Timber.e(error)
                })
    }
}