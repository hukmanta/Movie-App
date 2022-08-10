package com.example.movieApp.model.getReview


import com.google.gson.annotations.SerializedName

data class AuthorDetails(
    @SerializedName("name")
    val name: String?,
    @SerializedName("username")
    val username: String?, // a7a8524
    @SerializedName("avatar_path")
    val avatarPath: String?, // /https://www.gravatar.com/avatar/6e7edad7bbe0c599dfce11e289c2c82e.jpg
    @SerializedName("rating")
    val rating: Double? // 7.0
)