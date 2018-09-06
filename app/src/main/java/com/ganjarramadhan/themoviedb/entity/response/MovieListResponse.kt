package com.ganjarramadhan.themoviedb.entity.response

import com.google.gson.annotations.SerializedName

class MovieListResponse {

	@SerializedName("page")
	var page: Int? = null

	@SerializedName("total_pages")
	var totalPages: Int? = null

	@SerializedName("results")
	var results: List<Movie>? = null

	@SerializedName("total_results")
	var totalResults: Int? = null
}