package com.example.madlevel6task2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madlevel6task2.model.FilmResult
import com.example.madlevel6task2.repository.FilmRepository
import kotlinx.coroutines.launch

class FilmViewModel : ViewModel() {

    private val filmRepository = FilmRepository()

    val films: LiveData<FilmResult> = filmRepository.films

    fun getFilms(lang: String, year: Int) {
        viewModelScope.launch {
            filmRepository.getFilms(lang, year)
        }
    }
}


