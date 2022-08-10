package com.example.movieApp.model.getMovieDetail


import com.google.gson.annotations.SerializedName

data class BelongsToCollection(
    @SerializedName("id")
    val id: Int?, // 131296
    @SerializedName("name")
    val name: String?, // Thor Collection
    @SerializedName("poster_path")
    val posterPath: String?, // /yw7gr7DhHHVTLlO8Se8uH17TDMA.jpg
    @SerializedName("backdrop_path")
    val backdropPath: String? // /3KL8UNKFWgIKXzLHjwY0uwgjzYl.jpg
)