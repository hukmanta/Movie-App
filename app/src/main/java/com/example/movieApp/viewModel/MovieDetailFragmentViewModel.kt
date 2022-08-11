package com.example.movieApp.viewModel

import androidx.lifecycle.ViewModel
import com.example.movieApp.model.getMovieDetail.GetMovieDetailResponse
import com.example.movieApp.model.getReview.GetReviewResponse
import com.example.movieApp.model.getVideos.GetVideosResponse

class MovieDetailFragmentViewModel(var movieDetail: GetMovieDetailResponse, var review: GetReviewResponse, var video: GetVideosResponse) : ViewModel() {

    fun getTitle(): String =  if (movieDetail.title != null) "Judul: ${movieDetail.title}" else "Judul: -"
    fun getDesc(): String =  if (movieDetail.overview != null) "Uraian: ${movieDetail.overview}" else "Uraian: -"
    fun getReviewLabel(): String = "Review: "
    fun getVideoUrl() : String {
        val videoFilter =video.results?.filter { it?.type?.contains("trailer", true) ?: false }
        if (videoFilter != null && videoFilter.isNotEmpty()){
            return videoFilter[0]?.key?:""
        } else return ""
    }
}