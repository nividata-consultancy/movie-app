package com.nividata.owls.domain.data.model.response


data class MovieListResponse(
    val dates: DatesResponse,
    val page: Int,
    val results: List<MovieResponse>,
    val total_pages: Int,
    val total_results: Int,
    override val status: State = State.SUCCESS,
    override val message: String=""
) : BaseResponse

data class DatesResponse(
    val maximum: String,
    val minimum: String
)

data class MovieResponse(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)