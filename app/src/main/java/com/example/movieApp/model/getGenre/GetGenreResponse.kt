package com.example.movieApp.model.getGenre


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class GetGenreResponse(
    @SerializedName("genres")
    val genres: List<Genre?>?
) : Serializable