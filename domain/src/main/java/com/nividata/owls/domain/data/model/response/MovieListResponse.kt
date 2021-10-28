package com.nividata.owls.domain.data.model.response

import com.nividata.owls.domain.model.Movie


data class MovieListResponse(
    val dates: DatesResponse?,
    val page: Int,
    val results: List<MovieResponse>,
    val total_pages: Int,
    val total_results: Int,
    override val status: State = State.SUCCESS,
    override val message: String = ""
) : BaseResponse

data class DatesResponse(
    val maximum: String,
    val minimum: String
)

data class MovieResponse(
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int,

    val title: String,
    val original_title: String,
    val adult: Boolean,
    val release_date: String,
    val video: Boolean,
) {
    fun toMovie(): Movie = Movie(
        id = id,
        title = title,
        type = "movie",
        backdropPath = backdrop_path,
        posterPath = poster_path
    )
}