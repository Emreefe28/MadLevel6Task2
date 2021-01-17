package com.example.madlevel6task2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.api.FilmsApi
import com.example.madlevel6task2.model.FilmResult
import com.example.madlevel6task2.service.FilmApiService

class FilmRepository {
    private val filmApiService = FilmsApi.createApi()

    private val _films: MutableLiveData<FilmResult> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * Encapsulation :)
     */
    val films: LiveData<FilmResult>
        get() = _films

    /**
     * suspend function that calls a suspend function from the numbersApi call
     */
    suspend fun getFilms(lang: String, year: Int) {
        val filmsList = filmApiService.getFilms(lang, year)
        _films.value = filmsList
    }

}
