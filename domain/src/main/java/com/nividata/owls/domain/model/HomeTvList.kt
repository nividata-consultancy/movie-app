package com.nividata.owls.domain.model

data class HomeTvList(
    val id: Int,
    val title: String,
    val tvList: List<Movie>
)