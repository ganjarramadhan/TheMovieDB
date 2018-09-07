package com.ganjarramadhan.themoviedb.util.recyclerview.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ganjarramadhan.themoviedb.R

/**
 * Created by ganjarramadhan on 9/7/18.
 * ganjar.ramadhan05@gmail.com
 */
class RetryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup): RetryViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.state_retry, parent, false)
            return RetryViewHolder(view)
        }
    }


}