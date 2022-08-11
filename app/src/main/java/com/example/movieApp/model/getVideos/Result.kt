package com.example.movieApp.model.getVideos


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("iso_639_1")
    val iso6391: String?, // en
    @SerializedName("iso_3166_1")
    val iso31661: String?, // US
    @SerializedName("name")
    val name: String?, // THE BLACK PHONE Clip - Trick The Grabber (2022)
    @SerializedName("key")
    val key: String?, // Q8jzmUi3zas
    @SerializedName("site")
    val site: String?, // YouTube
    @SerializedName("size")
    val size: Int?, // 1080
    @SerializedName("type")
    val type: String?, // Clip
    @SerializedName("official")
    val official: Boolean?, // false
    @SerializedName("published_at")
    val publishedAt: String?, // 2022-06-16T14:01:35.000Z
    @SerializedName("id")
    val id: String? // 62ae67021bf2660051c5585c
)