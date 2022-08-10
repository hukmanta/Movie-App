package com.example.movieApp.model.getGenre


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id: Int?, // 28
    @SerializedName("name")
    val name: String? // Aksi
)