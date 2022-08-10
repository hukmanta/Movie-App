package com.example.movieApp.model.getMovieByGenre


import com.google.gson.annotations.SerializedName

data class GetMovieByGenreResponse(
    @SerializedName("page")
    val page: Int?, // 1
    @SerializedName("results")
    val results: List<Result?>?,
    @SerializedName("total_pages")
    val totalPages: Int?, // 8425
    @SerializedName("total_results")
    val totalResults: Int? // 168487
)