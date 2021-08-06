package br.com.mfet.rose.models

data class Movie(
    val id: Int,
    val title: String,
    val release_date: String,
    val overview: String,
    val poster_path: String,
    val genres: List<GenreModel>?,
    val adult: Boolean
    )
