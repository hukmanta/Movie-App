package com.example.movieApp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieApp.model.getReview.Result
import com.example.movieApp.R

@Suppress("DEPRECATION")
class ReviewRecyclerViewAdapter(
    private val context: Context,
    itemClickListener: RecyclerViewClickListener
) : RecyclerView.Adapter<ReviewRecyclerViewAdapter.ReviewVH>() {

    init {
        itemViewClickListener = itemClickListener
    }

    var data: List<Result?>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewVH {
        val view =
            LayoutInflater.from(context).inflate(R.layout.view_holder_review, parent, false)
        return ReviewVH(view)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ReviewVH, position: Int) {
        data?.get(position)?.let {
            holder.author.text = it.author
            holder.content.text = it.content
            holder.vote.text = "Rating: ${it.authorDetails?.rating.toString()}"
        }
    }

    companion object {
        private lateinit var itemViewClickListener:RecyclerViewClickListener
    }

    class ReviewVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val author: TextView = itemView.findViewById(R.id.authorLabel)
        val content: TextView = itemView.findViewById(R.id.contentLabel)
        val vote: TextView = itemView.findViewById(R.id.voteLabel)

        init {
            itemView.setOnClickListener {
                itemViewClickListener.recyclerViewListClick(it, this.layoutPosition)
            }
        }
    }
}