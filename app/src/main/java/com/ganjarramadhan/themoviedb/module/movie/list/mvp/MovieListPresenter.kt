package com.ganjarramadhan.themoviedb.module.movie.list.mvp

import com.ganjarramadhan.themoviedb.base.BasePresenter
import com.ganjarramadhan.themoviedb.entity.response.MovieListResponse
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by ganjarramadhan on 9/6/18.
 * ganjar.ramadhan05@gmail.com
 */
class MovieListPresenter(private val view: MovieListView, private val model: MovieListModel) : BasePresenter() {

    private val pageManager = PublishProcessor.create<Boolean>()
    private var currentPage = 1

    override fun onCreate() {
        mCompositeDisposable.add(subscribeLoadMovies())
        mCompositeDisposable.add(subscribeLoadMoreMovies())
        mCompositeDisposable.add(subscribeMovieClick())
        view.observeLoadMoreMovies(pageManager)
    }

    private fun subscribeMovieClick(): Disposable {
        return view.observeMovieItemClick()
                .subscribe { it ->
                    view.showMovieDetail(it)
                }
    }

    private fun subscribeLoadMoreMovies(): Disposable {
        return pageManager.onBackpressureDrop()
                .concatMap { isRefresh: Boolean ->
                    if (isRefresh) currentPage = 1
                    return@concatMap getMovies(currentPage)
                            .onErrorReturn { _ ->
                                return@onErrorReturn MovieListResponse()
                            }
                            .doOnNext {
                                if (isRefresh) {
                                    view.clearAllMovies()
                                }
                            }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    if (data.results?.isNotEmpty() == true) {
                        currentPage++
                        view.addMoviesToList(data.results, true)
                    } else {
                        Timber.e("Error")
                    }
                }, { error ->
                    view.showError(error.localizedMessage)
                })
    }

    private fun getMovies(page: Int): Flowable<MovieListResponse> {
        return model.getMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun subscribeLoadMovies(): Disposable {
        return model.getMovies(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    currentPage++
                    view.addMoviesToList(data.results, true)
                }, { error ->
                    view.showError(error.localizedMessage)
                })
    }

}