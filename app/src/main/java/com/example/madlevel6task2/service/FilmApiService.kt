package com.example.madlevel6task2.service

import com.example.madlevel6task2.model.FilmResult
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmApiService {

    // The GET method needed to retrieve a random number trivia.
    @GET(".")
    suspend fun getFilms(
        @Query("language") language: String,
        @Query("primary_release_year") releaseYear: Int
    ): FilmResult
}
