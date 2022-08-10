package com.example.movieApp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieApp.R
import com.example.movieApp.model.getMovieByGenre.Result

@Suppress("DEPRECATION")
class MovieRecyclerViewAdapter(
    private val context: Context,
    itemClickListener: RecyclerViewClickListener
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieVH>() {

    init {
        itemViewClickListener = itemClickListener
    }

    var data: List<Result?>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val view =
            LayoutInflater.from(context).inflate(R.layout.view_holder_movie, parent, false)
        return MovieVH(view)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        data?.get(position)?.let {
            holder.title.text = "Judul: ${it.title}"
            holder.release.text = "Tanggal Release: ${it.releaseDate}"
            holder.rating.text = "Rating: ${it.voteAverage.toString()}"
            Glide.with(context).load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/${it.posterPath}").into(holder.photo)
        }
    }

    companion object {
        private lateinit var itemViewClickListener:RecyclerViewClickListener
    }

    class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.cardView)
        val title: TextView = itemView.findViewById(R.id.movieLabel)
        val release: TextView = itemView.findViewById(R.id.releaseLabel)
        val rating: TextView = itemView.findViewById(R.id.voteLabel)

        init {
            itemView.setOnClickListener {
                itemViewClickListener.recyclerViewListClick(it, this.layoutPosition)
            }
        }
    }
}