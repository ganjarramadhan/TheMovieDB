package com.ganjarramadhan.themoviedb.module.movie.list.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ganjarramadhan.themoviedb.R
import com.ganjarramadhan.themoviedb.entity.response.Movie
import com.ganjarramadhan.themoviedb.module.movie.list.viewholder.MovieListViewHolder
import com.ganjarramadhan.themoviedb.util.recyclerview.InfiniteAdapterPlus
import com.ganjarramadhan.themoviedb.util.recyclerview.viewholder.LoadingViewHolder
import com.ganjarramadhan.themoviedb.util.recyclerview.viewholder.RetryViewHolder
import io.reactivex.subjects.PublishSubject

/**
 * Created by ganjarramadhan on 9/6/18.
 * ganjar.ramadhan05@gmail.com
 */
class MovieListAdapter(private val context: Context) : InfiniteAdapterPlus<MovieListViewHolder>() {

    var publishMovieClick = PublishSubject.create<Movie>()

    private val data = arrayListOf<Movie>()

    override val count: Int
        get() {
            return data.size
        }

    override fun getLoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return LoadingViewHolder.create(parent)
    }

    override fun getErrorViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return RetryViewHolder.create(parent)
    }

    override fun getViewType(position: Int): Int {
        return 2
    }

    override fun onCreateView(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return MovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieListViewHolder) {
            holder.bind(data[position])
            holder.itemView.setOnClickListener {
                publishMovieClick.onNext(data[position])
            }
        } else {
            super.onBindViewHolder(holder, position)
        }
    }

    fun insertData(results: List<Movie>) {
        val currentSize = count
        data.addAll(results)
        moreDataLoaded(currentSize, count - currentSize)

    }

    fun removeAll() {
        data.clear()
        notifyDataSetChanged()
    }
}