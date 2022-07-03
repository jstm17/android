package com.bellano.netflix

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bellano.themoviedblibrary.network.ApiHelper

class MainActivity : AppCompatActivity() {

  // var test = ApiHelper.getImageBaseUrl()

  lateinit var recyclerViewMovie: RecyclerView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    val logo: TextView = findViewById(R.id.textView)
    val imageSearch : ImageButton = findViewById(R.id.imageSearch)
    val searchBar : EditText = findViewById(R.id.searchBar)

    recyclerViewMovie = findViewById(R.id.recyclerViewMovie)

    logo.setOnClickListener{
      searchBar.text.clear()
      it.closeKeyboard()
      getAllFilms()
    }

    imageSearch.setOnClickListener{
      it.closeKeyboard()
      val searchValue: String = searchBar.text.toString()
      val films = getSearch(searchValue)
      if (searchValue.isNotEmpty()) {
        displayFilms(films)
      } else {
        getAllFilms()
      }
    }

    getAllFilms()
  }

  private fun View.closeKeyboard() {
    val input = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    input.hideSoftInputFromWindow(windowToken, 0)
  }

  private fun displayFilms(list: List<DisplayItem>) {
    recyclerViewMovie.apply{
      layoutManager = LinearLayoutManager(this@MainActivity)
      adapter = MovieAdapter(list)
    }
  }


  private fun getAllFilms() {
    val popular = getPopular()
    val top = getTop()
    val upcoming = getUpcoming()

    displayFilms(popular + top + upcoming)
  }

  private fun getPopular(): List<DisplayItem> {
    val getPopular = ApiHelper.getPopularMovies()
    val list = mutableListOf<Movie>()
    val results = getPopular?.results

    if (results != null) {
      for (result in results) {
        if (result.id !== null && result.title != null && result.release_date != null) {
          val film = Movie(result.id!!, result.title!!, result.release_date!!, result.poster_path as String?)
          list += film
        }
      }
    }

    return listOf(
      HeaderItem("Liste des films les plus populaires"),
    ) + list
  }

  private fun getTop(): List<DisplayItem> {
    val getTop = ApiHelper.getTopRated()
    val list = mutableListOf<Movie>()
    val results = getTop?.results

    if (results != null) {
      for (result in results) {
        if (result.id !== null && result.title != null && result.release_date != null) {
          val film = Movie(result.id!!, result.title!!, result.release_date!!, result.poster_path as String?)
          list += film
        }
      }
    }

    return listOf(
      HeaderItem("Liste des films les mieux notés"),
    ) + list
  }

  private fun getUpcoming(): List<DisplayItem> {
    val getUpcoming = ApiHelper.getUpcoming()
    val list = mutableListOf<Movie>()
    val results = getUpcoming?.results

    if (results != null) {
      for (result in results) {
        if (result.id !== null && result.title != null && result.release_date != null) {
          val film = Movie(result.id!!, result.title!!, result.release_date!!, result.poster_path as String?)
          list += film
        }
      }
    }

    return listOf(
      HeaderItem("Liste des films à venir"),
    ) + list
  }

  private fun getSearch(searchText: String): List<DisplayItem> {
    val getSearch = ApiHelper.searchMovie(searchText)
    val list = mutableListOf<Movie>()
    val results = getSearch?.results

    if (results != null) {
      for (result in results) {
        if (result.id !== null && result.title !== null && result.release_date != null) {
          val film = Movie(result.id!!, result.title!!, result.release_date!!, result.poster_path as String?)
          list += film
        }
      }
    }

    if (list.isNotEmpty()) {
      return listOf(
        HeaderItem("Résultats pour : $searchText"),
      ) + list
    }

    return listOf(
        HeaderItem("Pas de films trouvés"),
      )
  }

}