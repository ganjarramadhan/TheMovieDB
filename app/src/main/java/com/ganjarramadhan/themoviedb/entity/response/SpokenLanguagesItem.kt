package com.ganjarramadhan.themoviedb.entity.response

import com.google.gson.annotations.SerializedName

class SpokenLanguagesItem {

	@SerializedName("name")
	var name: String? = null

	@SerializedName("iso_639_1")
	var iso6391: String? = null
}