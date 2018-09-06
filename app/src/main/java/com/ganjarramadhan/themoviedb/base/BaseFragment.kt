package com.ganjarramadhan.themoviedb.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject

/**
 * Created by ganjarramadhan on 9/5/18.
 * ganjar.ramadhan05@gmail.com
 */

abstract class BaseFragment<T : BasePresenter> : Fragment() {

    @Inject
    protected lateinit var presenter: T

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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = setView()
        afterViewCreated(savedInstanceState)
        presenter.onCreate()
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }


}
