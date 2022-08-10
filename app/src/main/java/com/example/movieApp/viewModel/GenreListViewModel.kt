package com.example.movieApp.viewModel

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.movieApp.model.getGenre.GetGenreResponse

class GenreListViewModel(val listGenre: GetGenreResponse) :
    ViewModel() {
    private var isLoading:Int = View.GONE


}