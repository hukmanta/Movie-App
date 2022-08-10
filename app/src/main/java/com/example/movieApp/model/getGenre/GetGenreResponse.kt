package com.example.movieApp.model.getGenre


import com.google.gson.annotations.SerializedName


data class GetGenreResponse(
    @SerializedName("genres")
    val genres: List<Genre?>?
)