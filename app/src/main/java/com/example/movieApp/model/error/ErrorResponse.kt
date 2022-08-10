package com.example.movieApp.model.error


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status_message")
    val statusMessage: String?, // The resource you requested could not be found.
    @SerializedName("status_code")
    val statusCode: Int? // 34
)