package com.bellano.netflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bellano.myapplication.R

class MainActivity : AppCompatActivity() {

  // val topRated = ApiHelper.getTopRated()
  // topRated?.results

  lateinit var recyclerViewMovie: RecyclerView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)

    recyclerViewMovie = findViewById(R.id.recyclerViewMovie)

    val items = listOf(
      Movie("oui", "2099", R.drawable.homme_affaires_caractere_avatar_isole_24877_60111, 4F, "Thriller"),
      Movie("oui", "2099", R.drawable.homme_affaires_caractere_avatar_isole_24877_60111, 4F, "Thriller"),
      Movie("oui", "2099", R.drawable.homme_affaires_caractere_avatar_isole_24877_60111, 4F, "Thriller"),
      Movie("oui", "2099", R.drawable.homme_affaires_caractere_avatar_isole_24877_60111, 4F, "Thriller"),
      Movie("oui", "2099", R.drawable.homme_affaires_caractere_avatar_isole_24877_60111, 4F, "Thriller")
    )
    recyclerViewMovie.apply{
      layoutManager = LinearLayoutManager(this@MainActivity)
      adapter = MovieAdapter(items)
    }
  }
  //var count: Int = 0
  //override fun onCreate(savedInstanceState: Bundle?) {

  // super.onCreate(savedInstanceState)
    // Ancienne méthdoe
    // setContentView(R.layout.activity_main)

    // Nouvelle methode avec databinding
  //val binding: MainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)
  // binding.recyclerView.layoutManager = GridLayoutManager (this, 1) // LinearLayoutManager(this)

  // binding.recyclerView.adapter = MyAdapter(
  //   listOf(
  //     HeaderItem("Mes Diplômes"),
  //     Diplome("DUT GEII", 2008, "Université Lille 1"),
  //     Diplome("Licence Informatique", 2011, "Université Lille 1"),
  //     Diplome("Master Informatique", 2013, "Université Lille 1"),
  //     HeaderItem("Mes Experiences Pro"),
  //   )
  // )

  // binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

  // val topRated = ApiHelper.getTopRated()
  // topRated?.results

    //  Recuperer les references des vues
    //  Ancienne méthode
    //  val button: Button = findViewById(R.id.button)
    //  val textView: TextView = findViewById(R.id.textView)
    //  val textView = binding.textView

    //val button = binding.button

    // POsitioner le OnclickListener sur le bouton
    /*  button.setOnClickListener {
        // Incrementer le compteur + afficher la nouvelle valeur dans le texte
        // Ancienne methode
        // count = count.inc()
        // textView.text = "$count"

        binding.count = binding.count?.inc() ?: 0
      }*/

  //}

}