package com.bellano.netflix

import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.w3c.dom.Text

const val HEADER_TYPE = 0
const val MOVIE_TYPE = 1

class MovieAdapter(var items: List<DisplayItem>) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    open class MyViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView)

    class HeaderViewHolder(val view: View) : MyViewHolder(view) {
        val title: TextView = view.findViewById(R.id.itemHeader)
    }

    class MoviesViewsHolder(private val view: View) : MyViewHolder(view) {
        val title: TextView = view.findViewById(R.id.titleMovie)
        val releaseDate: TextView = view.findViewById(R.id.releaseDateMovie)
        val button : Button = view.findViewById(R.id.buttonNavig)
        val image: ImageView = view.findViewById(R.id.imageMovie)
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HeaderItem -> HEADER_TYPE
            is Movie -> MOVIE_TYPE
            else -> -1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return when (viewType) {
            HEADER_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
                HeaderViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
                MoviesViewsHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                val current = items[position] as HeaderItem
                holder.title.text = current.title
            }
            is MoviesViewsHolder -> {
                val current: Movie = items[position] as Movie
                val imageURL = current.poster_path?.let { "https://image.tmdb.org/t/p/original$it" } ?: "Image non trouvée"

                holder.title.text = current.title
                holder.releaseDate.text = current.release_date.toString()
                holder.image.load(imageURL)
                holder.button.setOnClickListener {
                    val intent = Intent(it.context, DetailsActivity::class.java)
                    intent.putExtra("id",current.id )
                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size
}

interface DisplayItem
data class HeaderItem(val title: String) : DisplayItem
data class Movie(val id: Int, val title: String, val release_date: String, val poster_path: String?) : DisplayItem



//class MovieAdapter(var items: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MoviesViewsHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewsHolder {
//
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
//        return MoviesViewsHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: MoviesViewsHolder, position: Int) {
//        val movie = items[position]
//        holder.bind(movie)
//    }
//
//    override fun getItemCount() = items.size
//
//    inner class MoviesViewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var title: TextView
//        var releaseDate: TextView
//        var ratingBar: RatingBar
//        var genres: TextView
//        var image: ImageView
//
//        init {
//            title = itemView.findViewById(R.id.titleMovie)
//            releaseDate = itemView.findViewById(R.id.releaseDateMovie)
//            ratingBar = itemView.findViewById(R.id.ratingBarMovie)
//            genres = itemView.findViewById(R.id.genresMovie)
//            image = itemView.findViewById(R.id.imageMovie)
//        }
//
//        fun bind(movie: Movie) {
//            title.text = movie.title
//            releaseDate.text = movie.releaseDate
//            genres.text = movie.genres
//            image.setImageResource(movie.image)
//            ratingBar.rating = movie.rating
//        }
//    }
//}