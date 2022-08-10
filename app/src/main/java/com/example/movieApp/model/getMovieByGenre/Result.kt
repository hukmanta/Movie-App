package com.example.movieApp.model.getMovieByGenre


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("adult")
    val adult: Boolean?, // false
    @SerializedName("backdrop_path")
    val backdropPath: String?, // /odJ4hx6g6vBt4lBWKFD1tI8WS4x.jpg
    @SerializedName("genre_ids")
    val genreIds: List<Int?>?,
    @SerializedName("id")
    val id: Int?, // 361743
    @SerializedName("original_language")
    val originalLanguage: String?, // en
    @SerializedName("original_title")
    val originalTitle: String?, // Top Gun: Maverick
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?, // 4624.654
    @SerializedName("poster_path")
    val posterPath: String?, // /jeGvNOVMs5QIU1VaoGvnd3gSv0G.jpg
    @SerializedName("release_date")
    val releaseDate: String?, // 2022-05-24
    @SerializedName("title")
    val title: String?, // Top Gun: Maverick
    @SerializedName("video")
    val video: Boolean?, // false
    @SerializedName("vote_average")
    val voteAverage: Double?, // 8.3
    @SerializedName("vote_count")
    val voteCount: Int? // 1827
)