package com.example.movieApp.model.getReview


import com.google.gson.annotations.SerializedName

data class GetReviewResponse(
    @SerializedName("id")
    val id: Int?, // 616037
    @SerializedName("page")
    val page: Int?, // 1
    @SerializedName("results")
    val results: List<Result?>?,
    @SerializedName("total_pages")
    val totalPages: Int?, // 1
    @SerializedName("total_results")
    val totalResults: Int? // 8
)