package com.ganjarramadhan.themoviedb.entity.response

import com.google.gson.annotations.SerializedName

class GenresItem {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int? = null

}