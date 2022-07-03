package com.bellano.netflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.bellano.themoviedblibrary.network.ApiHelper

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val movieId = intent.getIntExtra("id", 1)
        val movie = ApiHelper.getMovie(movieId.toString())
        val title = findViewById<TextView>(R.id.titleMovie)
        val image = findViewById<ImageView>(R.id.imageMovie)
        val date = findViewById<TextView>(R.id.releaseDateMovie)
        val description = findViewById<TextView>(R.id.description)
        title.text = movie?.title ?: "Titre non trouvé"
        image.load( movie?.poster_path?.let { "https://image.tmdb.org/t/p/original$it" } ?: "Image non trouvée")
        date.text = movie?.release_date ?: "Date non trouvée"
        description.text = movie?.overview ?: "Description non trouvée"



        val button = findViewById<Button>(R.id.buttonBack)
        button.setOnClickListener {
            this.finish()
        }
    }
}