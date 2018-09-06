package com.ganjarramadhan.themoviedb.entity.response

import com.google.gson.annotations.SerializedName

class RequestToken {

    @SerializedName("expires_at")
    var expiresAt: String? = null

    @SerializedName("success")
    var success: Boolean? = null

    @SerializedName("request_token")
    var requestToken: String? = null

}