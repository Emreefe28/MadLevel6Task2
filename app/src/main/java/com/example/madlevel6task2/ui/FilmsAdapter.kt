package com.example.madlevel6task2.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madlevel6task2.databinding.ItemPosterBinding
import com.example.madlevel6task2.model.Film

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FilmsAdapter(private val films: List<Film>, private val onClick: (Film) -> Unit) :
    RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemPosterBinding =
            ItemPosterBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(itemPosterBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(films[position])

    override fun getItemCount(): Int = films.size

    inner class ViewHolder(private val itemFilm: ItemPosterBinding) :
        RecyclerView.ViewHolder(itemFilm.root) {

        init {
            itemFilm.root.setOnClickListener {
                onClick(films[adapterPosition])
            }
        }

        fun bind(film: Film) {
            val position = (adapterPosition + 1).toString() + "."
            itemFilm.tvFilmPosition.text = position
            Glide.with(context).load(film.getPoster()).into(itemFilm.ivPoster)
        }
    }
}