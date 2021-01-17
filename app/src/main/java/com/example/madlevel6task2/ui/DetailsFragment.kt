package com.example.madlevel6task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.example.madlevel6task2.databinding.FragmentDetailsBinding
import com.example.madlevel6task2.viewmodel.FilmSharedViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DetailsFragment : Fragment() {
    private val filmsViewModel: FilmSharedViewModel by activityViewModels()
    private lateinit var fragmentDetailsBinding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentDetailsBinding = FragmentDetailsBinding.inflate(layoutInflater)
        return fragmentDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeFilm()
    }

    private fun observeFilm() {
        filmsViewModel.selectedFilm.observe(viewLifecycleOwner) {
            Glide.with(this).load(it.getBackDrop())
                .into(fragmentDetailsBinding.ivBackdropImage)
            Glide.with(this).load(it.getPoster())
                .into(fragmentDetailsBinding.ivPosterImage)
            fragmentDetailsBinding.tvFilmTitle.text = it.title
            fragmentDetailsBinding.tvFilmDate.text = it.releaseDate
            fragmentDetailsBinding.tvStarRating.text = it.rating.toString()
            fragmentDetailsBinding.tvOverviewText.text = it.overview
        }
    }
}