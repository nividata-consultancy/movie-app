package com.nividata.owls.domain

import com.nividata.owls.domain.data.model.response.MovieResponse

data class HomeMovieList(
    val id: Int,
    val title: String,
    val movieList: List<MovieResponse>
)