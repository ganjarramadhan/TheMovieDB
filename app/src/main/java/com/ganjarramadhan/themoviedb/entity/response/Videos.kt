package com.ganjarramadhan.themoviedb.entity.response

import com.google.gson.annotations.SerializedName

class Videos {

    @SerializedName("results")
    var results: List<VideosItem?>? = null
}