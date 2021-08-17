package com.nividata.owls.domain.model

import com.nividata.owls.domain.data.model.response.Genres

data class GenreTypeWise(
    val movieGenre: List<Genres>,
    val tvGenre: List<Genres>
)
