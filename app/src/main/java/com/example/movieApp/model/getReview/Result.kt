package com.example.movieApp.model.getReview


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("author")
    val author: String?, // a7a8524
    @SerializedName("author_details")
    val authorDetails: AuthorDetails?,
    @SerializedName("content")
    val content: String?, // Pretty good ngl. Something new i enjoyed it
    @SerializedName("created_at")
    val createdAt: String?, // 2022-05-05T20:05:40.542Z
    @SerializedName("id")
    val id: String?, // 62742e148e2e0000a97e3876
    @SerializedName("updated_at")
    val updatedAt: String?, // 2022-06-15T19:19:20.718Z
    @SerializedName("url")
    val url: String? // https://www.themoviedb.org/review/62742e148e2e0000a97e3876
)