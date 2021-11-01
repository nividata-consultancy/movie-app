package com.nividata.owls.domain.model

data class HomeMovieList(
    val id: Int,
    val title: String,
    val categoryType: String? = null,
    val movieList: List<Movie>,
    val totalPages: Int? = null,
    val page: Int? = null,
)