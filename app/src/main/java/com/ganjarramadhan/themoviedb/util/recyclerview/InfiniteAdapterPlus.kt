package com.ganjarramadhan.themoviedb.util.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * modified by ganjarramadhan on 18/10/17.
 * ganjar.ramadhan05@gmail.com
 *
 * Modified by iamtheib.
 *
 * original version created by Saurabh on 6/2/16.
 *
 * This supports a callback to notify when to load more items.
 *
 * Implementing Activities/fragments should also call moreDataLoaded(int, int)
 * to inform the adapter that more data has been loaded.
 */
abstract class InfiniteAdapterPlus<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mShouldLoadMore = true
    private var mIsLoading = false
    private var mIsError = false
    // Used to indicate the infinite scrolling should be bottom up
    private var mIsReversedScrolling = false

    var loadMoreListener: Listener? = null
    /**
     * Registers a callback to be notified when error shown
     */
    var errorListener: OnErrorListener? = null

    /**
     * Returns the number of scrollable items that should be left (threshold) in the
     * list before `Listener` will be called
     *
     * You can override this to return a preffered threshold,
     * or leave it to use the default
     *
     * @return integer threshold
     */
    private val visibleThreshold: Int = 5

    /**
     * The count of the number of items in the list. This does not include the loading item
     *
     * @return number of items in list
     */
    abstract val count: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VIEW_TYPE_LOADING -> return getLoadingViewHolder(parent)
            VIEW_TYPE_ERROR_RETRY -> return getErrorViewHolder(parent)
            else -> return onCreateView(parent, viewType)
        }
    }

    /**
     * Subclasses should override this method, to actually bind the view data,
     * but always call `super.onBindViewHolder(holder, position)`
     * to enable the adapter to calculate whether the load more callback should be invoked
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (mShouldLoadMore && !mIsLoading) {
            val threshold = visibleThreshold
            val hasReachedThreshold = if (mIsReversedScrolling) position <= threshold else position >= count - threshold
            if (hasReachedThreshold) {
                mIsLoading = true
                if (loadMoreListener != null) {
                    loadMoreListener!!.onLoadMore()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        val actualCount = count
        // So as to avoid nasty index calculations, having reversed scrolling does
        // not affect the item count.
        // The consequence of this is, while there is more data to load, the first item on
        // the list will be replaced by the loading view
        return if (actualCount == 0 || !mShouldLoadMore && !mIsError || mIsReversedScrolling) {
            actualCount
        } else {
            actualCount + 1
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (isLoadingView(position)) {
            return VIEW_TYPE_LOADING
        } else if (isErrorView(position)) {
            return VIEW_TYPE_ERROR_RETRY
        } else {
            val viewType = getViewType(position)
            return if (viewType == VIEW_TYPE_LOADING) {
                throw IndexOutOfBoundsException("0 index is reserved for the loading view")
            } else if (viewType == VIEW_TYPE_LOADING) {
                throw IndexOutOfBoundsException("1 index is reserved for the error view")
            } else {
                viewType
            }
        }
    }

    private fun isLoadingView(position: Int): Boolean {
        // For reversed scrolling, the loading view is always the top one
        val loadingViewPosition = if (mIsReversedScrolling) 0 else count
        return position == loadingViewPosition && mShouldLoadMore
    }

    private fun isErrorView(position: Int): Boolean {
        // For reversed scrolling, the error view is always the top one
        val errorViewPosition = if (mIsReversedScrolling) 0 else count
        return position == errorViewPosition && mIsError
    }

    /**
     * Set as false when you don't want the recycler view to load more data.
     * This will also remove the loading view
     */
    fun setShouldLoadMore(shouldLoadMore: Boolean) {
        this.mShouldLoadMore = shouldLoadMore
    }

    /**
     * Set as true when you want the recycler view to show error when load more.
     * It will remove the loading view and showing error view
     */
    fun showError(showError: Boolean) {
        this.mIsError = showError
        setShouldLoadMore(!showError)
        notifyItemChanged(itemCount - 1)
        if (!showError) {
            mIsLoading = true
            notifyItemChanged(itemCount - 1)
            if (loadMoreListener != null) {
                loadMoreListener!!.onLoadMore()
            }
        }
    }

    /**
     * Set as true if you want the endless scrolling to be as the user scrolls
     * to the top of the list, instead of bottom
     */
    fun setIsReversedScrolling(reversed: Boolean) {
        this.mIsReversedScrolling = reversed
    }

    /**
     * Registers a callback to be notified when there is a need to load more data
     */
    fun setOnLoadMoreListener(listener: Listener) {
        this.loadMoreListener = listener
    }

    /**
     * This informs the adapter that `itemCount` more data has been loaded,
     * starting from `positionStart`
     *
     * This also calls `notifyItemRangeInserted(int, int)`,
     * so the implementing class only needs to call this method
     *
     * @param positionStart Position of the first item that was inserted
     * @param itemCount Number of items inserted
     */
    fun moreDataLoaded(positionStart: Int, itemCount: Int) {
        mIsLoading = false
        notifyItemRemoved(positionStart) // remove the loading view
        notifyItemRangeInserted(positionStart, itemCount)
    }

    /**
     * Returns the loading view to be shown at the bottom of the list.
     *
     * @return loading view
     */
    abstract fun getLoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    /**
     * Returns the error view to be shown when load more failed
     *
     * @return error view
     */
    abstract fun getErrorViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    /**
     * Return the view type of the item at `position` for the purposes
     * of view recycling.
     *
     *
     * 0 index is reserved for the loading view. So this function cannot return 0.
     *
     * @param position position to query
     * @return integer value identifying the type of the view needed to represent the item at
     * `position`. Type codes need not be contiguous.
     */
    abstract fun getViewType(position: Int): Int

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent
     * an item. This is the same as the onCreateViewHolder method in RecyclerView.Adapter,
     * except that it internally detects and handles the creation on the loading footer
     */
    abstract fun onCreateView(parent: ViewGroup, viewType: Int): VH

    interface Listener {
        fun onLoadMore()
    }

    interface OnErrorListener {
        fun onRetryLoadMore()
    }

    companion object {
        private val VIEW_TYPE_LOADING = 0
        private val VIEW_TYPE_ERROR_RETRY = 1
    }
}
