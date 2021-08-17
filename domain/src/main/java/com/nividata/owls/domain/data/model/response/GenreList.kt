package com.nividata.owls.domain.data.model.response

class GenreList(
    val genres: List<Genres>
)

data class Genres(
    val id: Int,
    val name: String
)