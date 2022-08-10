package com.example.movieApp.service

import com.example.movieApp.BuildConfig
import com.example.movieApp.model.getGenre.GetGenreResponse
import com.example.movieApp.model.getMovieByGenre.GetMovieByGenreResponse
import com.example.movieApp.model.getMovieDetail.GetMovieDetailResponse
import com.example.movieApp.model.getReview.GetReviewResponse
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
     *@param parameters => hashMapOf("language" to "id","page" to 1)
     * @param id => movie_ID : Int
     * **/
    @GET("movie/{id}/reviews")
    @Throws(ConnectException::class)
    suspend fun getUserReviewAsync(@Header("Authorization") accessToken: String = "Bearer ${BuildConfig.API_KEY}", @Path(value = "id", encoded = true) id: Int, @QueryMap(encoded = true) parameters: Map<String, Any>): GetReviewResponse


    /***
     * @return [GetMovieDetailResponse]
     * @param id => movie_ID : Int
     * **/
    @GET("genre/movie/{id}")
    @Throws(ConnectException::class)
    suspend fun getMovieDetailAsync(@Header("Authorization") accessToken: String = "Bearer ${BuildConfig.API_KEY}", @Path(value = "id", encoded = true)id: Int, @Query("language") language: String="id"): GetMovieDetailResponse
}