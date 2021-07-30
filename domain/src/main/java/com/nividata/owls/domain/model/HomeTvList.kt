package com.nividata.owls.domain.model

import com.nividata.owls.domain.data.model.response.MovieResponse

data class HomeTvList(
    val id: Int,
    val title: String,
    val tvList: List<MovieResponse>
)