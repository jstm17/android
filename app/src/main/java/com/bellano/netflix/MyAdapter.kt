package com.bellano.netflix

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

const val HEADER_TYPE = 0
const val DIPLOME_TYPE = 1

class MyAdapter(val data: List<DisplayItem>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

  open class MyViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView)

  class HeaderViewHolder(val view: View) : MyViewHolder(view) {
    val title: TextView = view.findViewById(R.id.title)
  }

  class DiplomeViewHolder(private val view: View) : MyViewHolder(view) {
    val title: TextView = view.findViewById(R.id.title)
    val annee = view.findViewById<TextView>(R.id.annee)
    val ecole = view.findViewById<TextView>(R.id.ecole)
  }

  override fun getItemViewType(position: Int): Int {
    return when (data[position]) {
      is HeaderItem -> HEADER_TYPE
      is Diplome -> DIPLOME_TYPE
      else -> -1
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    println("onCreateViewHolder pour le type $viewType")
    return when (viewType) {
      HEADER_TYPE -> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
        HeaderViewHolder(view)
      }
      else -> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        DiplomeViewHolder(view)
      }
    }
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    when (holder) {
      is HeaderViewHolder -> {
        val current = data[position] as HeaderItem
        holder.title.text = current.title
      }
      is DiplomeViewHolder -> {
        val current: Diplome = data[position] as Diplome

        holder.title.text = current.title
        holder.annee.text = current.annee.toString()
        holder.ecole.text = current.ecole
      }
    }
  }

  override fun getItemCount(): Int = data.size

}

interface DisplayItem
data class HeaderItem(val title: String) : DisplayItem
data class Diplome(val title: String, val annee: Int, val ecole: String) : DisplayItem