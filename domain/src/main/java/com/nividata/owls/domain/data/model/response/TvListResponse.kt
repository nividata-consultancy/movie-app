package com.nividata.owls.domain.data.model.response

import com.nividata.owls.domain.model.Movie


data class TvListResponse(
//    val dates: DatesResponse?,
    val page: Int,
    val results: List<TvResponse>,
    val total_pages: Int,
    val total_results: Int,
    override val status: State = State.SUCCESS,
    override val message: String = ""
) : BaseResponse

//data class DatesResponse(
//    val maximum: String,
//    val minimum: String
//)

data class TvResponse(
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val vote_average: Double,
    val vote_count: Int,

    val name: String,
    val original_name: String,
    val origin_country: List<String>,
    val first_air_date: String?,
) {
    fun toMovie(): Movie = Movie(
        id = id,
        title = name,
        type = "tv",
        backdropPath = backdrop_path,
        posterPath = poster_path
    )
}