package com.example.movieApp.model.getVideos


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetVideosResponse(
    @SerializedName("id")
    val id: Int?, // 756999
    @SerializedName("results")
    val results: List<Result?>?
) : Serializable