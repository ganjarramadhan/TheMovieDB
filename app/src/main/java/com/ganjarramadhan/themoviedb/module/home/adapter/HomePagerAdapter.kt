package com.ganjarramadhan.themoviedb.module.home.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ganjarramadhan.themoviedb.R

/**
 * Created by ganjarramadhan on 9/6/18.
 * ganjar.ramadhan05@gmail.com
 */
class HomePagerAdapter(private val context: Context,
                       fragmentManager: FragmentManager,
                       private val fragmentList: List<Fragment>)
    : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                context.resources.getString(R.string.popular)
            }
            else -> {
                context.resources.getString(R.string.top_rated)
            }
        }
    }
}