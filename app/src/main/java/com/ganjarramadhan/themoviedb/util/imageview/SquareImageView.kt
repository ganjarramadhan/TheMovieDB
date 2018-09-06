package com.ganjarramadhan.themoviedb.util.imageview

import android.content.Context
import android.util.AttributeSet

/**
 * Created by ganjarramadhan on 9/5/18.
 * ganjar.ramadhan05@gmail.com
 */

class SquareImageView : android.support.v7.widget.AppCompatImageView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        setMeasuredDimension(width, width)
    }


}