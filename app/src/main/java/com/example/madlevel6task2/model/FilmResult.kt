package com.example.madlevel6task2.model

import com.google.gson.annotations.SerializedName

class FilmResult {
    @SerializedName("results")
    var films: List<Film> = arrayListOf()
}