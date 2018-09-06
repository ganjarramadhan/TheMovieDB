package com.ganjarramadhan.themoviedb.entity.response

import com.google.gson.annotations.SerializedName

class Movie {

    @SerializedName("genre_ids")
    var genreIds: List<Int?>? = null

    @SerializedName("original_language")
    var originalLanguage: String? = null

    @SerializedName("imdb_id")
    var imdbId: String? = null

    @SerializedName("videos")
    var videos: Videos? = null

    @SerializedName("video")
    var video: Boolean? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("revenue")
    var revenue: Int? = null

    @SerializedName("genres")
    var genres: List<GenresItem?>? = null

    @SerializedName("popularity")
    var popularity: Double? = null

    @SerializedName("production_countries")
    var productionCountries: List<ProductionCountriesItem?>? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("vote_count")
    var voteCount: Int? = null

    @SerializedName("budget")
    var budget: Int? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("original_title")
    var originalTitle: String? = null

    @SerializedName("runtime")
    var runtime: Int? = null

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguagesItem?>? = null

    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompaniesItem?>? = null

    @SerializedName("release_date")
    var releaseDate: String? = null

    @SerializedName("vote_average")
    var voteAverage: Double? = null

    @SerializedName("belongs_to_collection")
    var belongsToCollection: Any? = null

    @SerializedName("tagline")
    var tagline: String? = null

    @SerializedName("adult")
    var adult: Boolean? = null

    @SerializedName("homepage")
    var homepage: String? = null

    @SerializedName("status")
    var status: String? = null
}