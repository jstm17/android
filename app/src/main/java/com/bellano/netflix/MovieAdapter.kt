package com.bellano.netflix

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(var items: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MoviesViewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewsHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviesViewsHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewsHolder, position: Int) {
        val movie = items[position]
        holder.bind(movie)
    }

    override fun getItemCount() = items.size

    inner class MoviesViewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var releaseDate: TextView
        var ratingBar: RatingBar
        var genres: TextView
        var image: ImageView

        init {
            title = itemView.findViewById(R.id.titleMovie)
            releaseDate = itemView.findViewById(R.id.releaseDateMovie)
            ratingBar = itemView.findViewById(R.id.ratingBarMovie)
            genres = itemView.findViewById(R.id.genresMovie)
            image = itemView.findViewById(R.id.imageMovie)
        }

        fun bind(movie: Movie) {
            title.text = movie.title
            releaseDate.text = movie.releaseDate
            genres.text = movie.genres
            image.setImageResource(movie.image)
            ratingBar.rating = movie.rating
        }
    }
}