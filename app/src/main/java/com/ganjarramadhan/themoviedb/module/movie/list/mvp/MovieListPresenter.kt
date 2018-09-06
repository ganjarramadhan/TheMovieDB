package com.ganjarramadhan.themoviedb.module.movie.list.mvp

import com.ganjarramadhan.themoviedb.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ganjarramadhan on 9/6/18.
 * ganjar.ramadhan05@gmail.com
 */
class MovieListPresenter(private val view: MovieListView, private val model: MovieListModel): BasePresenter() {

    override fun onCreate() {
        mCompositeDisposable.add(subscribeLoadPopularMovies())
    }

    private fun subscribeLoadPopularMovies(): Disposable {
        return model.getMovies(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { data ->
                    view.addMoviesToList(data.results)
                }
    }

}