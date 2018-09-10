package com.ganjarramadhan.themoviedb.util.extension

import android.content.res.Resources
import com.ganjarramadhan.themoviedb.app.constant.AppConstant
import com.ganjarramadhan.themoviedb.entity.response.GenresItem


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

fun List<GenresItem?>.toGenreString(): String {
    var result = ""
    forEachIndexed { index, genresItem ->
        if (index == size - 1) {
            result = "$result${genresItem?.name}"
        } else {
            result = "$result${genresItem?.name}, "
        }
    }
    return result
}
