package com.example.madlevel6task2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.madlevel6task2.model.Film

class FilmSharedViewModel : ViewModel() {
    private var _selectedFilm: MutableLiveData<Film> = MutableLiveData()

    val selectedFilm: LiveData<Film>
        get() = _selectedFilm

    fun select(film: Film) {
        _selectedFilm.value = film
    }
}


