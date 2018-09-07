package com.ganjarramadhan.themoviedb.module.home

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.ganjarramadhan.themoviedb.R
import com.ganjarramadhan.themoviedb.module.home.adapter.HomePagerAdapter
import com.ganjarramadhan.themoviedb.module.movie.list.MovieListFragment
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by ganjarramadhan on 9/4/18.
 * ganjar.ramadhan05@gmail.com
 */
class HomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)

        // setup tab layout
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.setupWithViewPager(viewPager)

        // assign fragment
        val fragmentListPopularMovie = MovieListFragment.newInstance(MovieListFragment.LIST_POPULAR)
        val fragmentListTopRatedMovie = MovieListFragment.newInstance(MovieListFragment.LIST_TOP_RATED)

        val fragmentList = arrayListOf<Fragment>(fragmentListPopularMovie,
                fragmentListTopRatedMovie)

        viewPager.adapter = HomePagerAdapter(this, supportFragmentManager, fragmentList)
        viewPager.offscreenPageLimit = 2

    }

}