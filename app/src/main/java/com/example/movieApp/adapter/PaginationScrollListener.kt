package com.example.movieApp.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(
    private var layoutManager: LinearLayoutManager,
    private  var totalPage: Int,
    private var currentPage: Int
) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (currentPage < totalPage) {
            if (visibleItemCount + firstVisibleItemPosition > totalItemCount &&
                firstVisibleItemPosition > 0
            ) {
                currentPage+=1
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()
}