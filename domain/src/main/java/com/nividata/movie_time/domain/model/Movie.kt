package com.nividata.movie_time.domain.model

data class Movie(
    val id: Int,
    val type: String,
    val title: String,
    val backdropPath: String?,
    val posterPath: String?
)
