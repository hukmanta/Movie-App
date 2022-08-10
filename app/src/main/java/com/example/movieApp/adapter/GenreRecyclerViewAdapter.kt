package com.example.movieApp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieApp.R
import com.example.movieApp.model.getGenre.Genre

@Suppress("DEPRECATION")
class GenreRecyclerViewAdapter(
    private val context: Context,
    itemClickListener: RecyclerViewClickListener
) : RecyclerView.Adapter<GenreRecyclerViewAdapter.GenreVH>() {

    init {
        itemViewClickListener = itemClickListener
    }

    var data: List<Genre?>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreVH {
        val view =
            LayoutInflater.from(context).inflate(R.layout.view_holder_genre, parent, false)
        return GenreVH(view)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: GenreVH, position: Int) {
        data?.get(position)?.let {
            holder.title.text = it.name
        }
    }

    companion object {
        private lateinit var itemViewClickListener:RecyclerViewClickListener
    }

    class GenreVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.genreLabel)

        init {
            itemView.setOnClickListener {
                itemViewClickListener.recyclerViewListClick(it, this.layoutPosition)
            }
        }
    }
}