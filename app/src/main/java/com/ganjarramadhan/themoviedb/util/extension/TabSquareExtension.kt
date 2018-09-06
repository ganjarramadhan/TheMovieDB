package com.ganjarramadhan.themoviedb.util.extension

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.ganjarramadhan.themoviedb.app.constant.AppConstant


/**
 * Created by ganjarramadhan on 9/5/18.
 * ganjar.ramadhan05@gmail.com
 */

fun String.toMovieDbImageUrl(): String {
    return "${AppConstant.BASE_URL_IMAGE}${this}"
}

fun Int.toPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
