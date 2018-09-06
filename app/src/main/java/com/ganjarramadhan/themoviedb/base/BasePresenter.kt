package com.ganjarramadhan.themoviedb.base

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by ganjarramadhan on 9/5/18.
 * ganjar.ramadhan05@gmail.com
 */

abstract class BasePresenter {

    protected val mCompositeDisposable = CompositeDisposable()

    abstract fun onCreate()

    open fun onDestroy() {
        mCompositeDisposable.clear()
    }

    open fun onResume() {
        // do nothing
    }

}
