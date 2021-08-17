package com.nividata.owls.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val backdropPath: String?,
    val posterPath: String?
)
