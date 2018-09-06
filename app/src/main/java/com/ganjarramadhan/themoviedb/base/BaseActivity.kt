package com.ganjarramadhan.themoviedb.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import javax.inject.Inject

/**
 * Created by ganjarramadhan on 9/5/18.
 * ganjar.ramadhan05@gmail.com
 */

abstract class BaseActivity<T : BasePresenter> : AppCompatActivity() {

    @Inject
    lateinit var presenter: T

    /**
     * action before set content mPart1View
     */
    abstract fun beforeViewCreated()

    /**
     * just pass R.id.layout_name
     *
     * @return View
     */
    abstract fun setView(): View

    /**
     * action after set content mPart1View
     */
    abstract fun afterViewCreated(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beforeViewCreated()

        setContentView(setView())

        afterViewCreated(savedInstanceState)

        presenter.onCreate()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

}
