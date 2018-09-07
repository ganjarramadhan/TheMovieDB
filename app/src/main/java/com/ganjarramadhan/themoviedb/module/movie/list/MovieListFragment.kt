package com.ganjarramadhan.themoviedb.module.movie.list

import android.os.Bundle
import android.view.View
import com.ganjarramadhan.themoviedb.app.TheMovieDbApp
import com.ganjarramadhan.themoviedb.base.BaseFragment
import com.ganjarramadhan.themoviedb.module.movie.list.dagger.DaggerMovieListComponent
import com.ganjarramadhan.themoviedb.module.movie.list.dagger.MovieListModule
import com.ganjarramadhan.themoviedb.module.movie.list.mvp.MovieListPresenter
import com.ganjarramadhan.themoviedb.module.movie.list.mvp.MovieListView
import javax.inject.Inject

/**
 * Created by ganjarramadhan on 9/5/18.
 * ganjar.ramadhan05@gmail.com
 */
class MovieListFragment: BaseFragment<MovieListPresenter>() {

    @Inject
    lateinit var view: MovieListView

    override fun beforeViewCreated() {
        DaggerMovieListComponent.builder()
                .appComponent(TheMovieDbApp[activity!!].appComponent)
                .movieListModule(MovieListModule(this))
                .build().inject(this)
    }

    override fun setView(): View {
        return view
    }

    override fun afterViewCreated(savedInstanceState: Bundle?) {
    }


    companion object {

        const val LIST_TYPE = "type"

        const val LIST_POPULAR = 1
        const val LIST_TOP_RATED = 2

        fun newInstance(type: Int): MovieListFragment {
            val bundle = Bundle()
            bundle.putInt(LIST_TYPE, type)

            val fragment = MovieListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}