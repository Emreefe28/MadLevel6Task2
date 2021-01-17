package com.example.madlevel6task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.madlevel6task2.R
import com.example.madlevel6task2.databinding.FragmentFilmsBinding
import com.example.madlevel6task2.model.Film
import com.example.madlevel6task2.model.FilmLanguage
import com.example.madlevel6task2.viewmodel.FilmSharedViewModel
import com.example.madlevel6task2.viewmodel.FilmViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FilmsFragment : Fragment() {
    private val viewModel: FilmViewModel by viewModels()
    private val sharedViewModel: FilmSharedViewModel by activityViewModels()
    private lateinit var fragmentFilmsBinding: FragmentFilmsBinding

    private val filmsList = arrayListOf<Film>()
    private lateinit var filmsAdapter: FilmsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentFilmsBinding = FragmentFilmsBinding.inflate(layoutInflater)
        return fragmentFilmsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filmsAdapter = FilmsAdapter(filmsList, this::onFilmClick)
        fragmentFilmsBinding.rvFilms.layoutManager =
            GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        fragmentFilmsBinding.rvFilms.adapter = filmsAdapter

        fragmentFilmsBinding.btnSubmit.setOnClickListener {

            val filmLanguage = FilmLanguage.EnglishUS
            val filmYear = fragmentFilmsBinding.etYear.text.toString()

            if (filmYear.isNotBlank()) {
                viewModel.getFilms(filmLanguage.language, filmYear.toInt())
            }
        }

        observeFilms()
    }

    private fun observeFilms() {
        viewModel.films.observe(viewLifecycleOwner) {
            filmsList.clear()
            filmsList.addAll(it.films)
            filmsAdapter.notifyDataSetChanged()
        }
    }

    private fun onFilmClick(film: Film) {
        sharedViewModel.select(film)
        findNavController().navigate(R.id.action_FirstFragment_to_detailsFragment)
    }
}