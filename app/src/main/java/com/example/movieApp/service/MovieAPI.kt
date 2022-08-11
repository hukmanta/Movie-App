package com.example.movieApp.service

import com.example.movieApp.BuildConfig
import com.example.movieApp.model.getGenre.GetGenreResponse
import com.example.movieApp.model.getMovieByGenre.GetMovieByGenreResponse
import com.example.movieApp.model.getMovieDetail.GetMovieDetailResponse
import com.example.movieApp.model.getReview.GetReviewResponse
import com.example.movieApp.model.getVideos.GetVideosResponse
import retrofit2.http.*
import java.net.ConnectException

interface MovieAPI {

    /***
     * @return [GetGenreResponse]
     * **/
    @GET("genre/movie/list")
    @Throws(ConnectException::class)
    suspend fun getGenreAsync(@Header("Authorization") accessToken: String = "Bearer ${BuildConfig.API_KEY}", @Query("language") language: String="id"): GetGenreResponse

    /***
    * @return [GetMovieByGenreResponse]
    * **/
    @GET("discover/movie")
    @Throws(ConnectException::class)
    suspend fun getMovieByGenreAsync(@Header("Authorization") accessToken: String = "Bearer ${BuildConfig.API_KEY}", @Query("language") language: String="id", @Query("with_genres")with_genres: Int, @Query("page")page: Int=1): GetMovieByGenreResponse

    /***
     * @return [GetReviewResponse]
     * @param id => movie_ID : Int
     * **/
    @GET("movie/{id}/reviews")
    @Throws(ConnectException::class)
    suspend fun getUserReviewAsync(@Header("Authorization") accessToken: String = "Bearer ${BuildConfig.API_KEY}", @Path("id") id: Int, @Query("language") language: String="id", @Query("page")page: Int=1): GetReviewResponse


    /***
     * @return [GetMovieDetailResponse]
     * @param id => movie_ID : Int
     * **/
    @GET("movie/{id}")
    @Throws(ConnectException::class)
    suspend fun getMovieDetailAsync(@Header("Authorization") accessToken: String = "Bearer ${BuildConfig.API_KEY}", @Path("id")id: Int, @Query("language") language: String="id"): GetMovieDetailResponse

    /***
     * @return [GetVideosResponse]
     * @param id => movie_ID : Int
     * **/
    @GET("movie/{id}/videos")
    @Throws(ConnectException::class)
    suspend fun getVideoAsync(@Header("Authorization") accessToken: String = "Bearer ${BuildConfig.API_KEY}", @Path("id") id: Int, @Query("language") language: String="id"): GetVideosResponse
}