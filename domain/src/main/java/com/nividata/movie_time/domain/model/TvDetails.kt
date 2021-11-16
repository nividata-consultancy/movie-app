package com.nividata.movie_time.domain.model

class TvDetails(
    val backdrop_path: String?,
    val created_by: List<CreatedBy>,
    val episode_run_time: List<Int>,
    val first_air_date: String,
    val genres: List<Genres>,
    val homepage: String,
    val id: Int,
    val in_production: Boolean,
    val languages: List<String>,
    val last_air_date: String?,
    val last_episode_to_air: EpisodeToAir?,
    val name: String,
    val next_episode_to_air: EpisodeToAir?,
    val networks: List<Networks>,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<ProductionCompanies>,
    val production_countries: List<ProductionCountries>,
    val seasons: List<Seasons>,
    val spoken_languages: List<SpokenLanguages>,
    val status: String,
    val tagline: String,
    val type: String,
    val vote_average: Double,
    val vote_count: Int
) {
    data class CreatedBy(
        val id: Int,
        val credit_id: String,
        val name: String,
        val gender: Int?,
        val profile_path: String?
    )

    data class EpisodeToAir(
        val air_date: String,
        val episode_number: Int,
        val id: Int,
        val name: String,
        val overview: String,
        val production_code: String,
        val season_number: Int,
        val still_path: String?,
        val vote_average: Double,
        val vote_count: Int
    )

    data class Networks(
        val name: String,
        val id: Int,
        val logo_path: String?,
        val origin_country: String
    )

    data class Seasons(
        val air_date: String?,
        val episode_count: Int,
        val id: Int,
        val name: String,
        val overview: String,
        val poster_path: String?,
        val season_number: Int
    )
}