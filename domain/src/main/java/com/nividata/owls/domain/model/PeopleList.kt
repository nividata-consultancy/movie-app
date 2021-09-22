package com.nividata.owls.domain.model

data class PeopleList(
    val page : Int,
    val results : List<People>,
    val total_pages : Int,
    val total_results : Int
)
data class People (
    val adult : Boolean,
    val gender : Int,
    val id : Int,
    val known_for : List<KnownFor>,
    val known_for_department : String,
    val name : String,
    val popularity : Double,
    val profile_path : String
)
data class KnownFor (
    val backdrop_path : String?,
    val first_air_date : String?,
    val genre_ids : List<Int>,
    val id : Int,
    val media_type : String,
    val name : String?,
    val origin_country : List<String>?,
    val original_language : String,
    val original_name : String?,
    val overview : String,
    val poster_path : String,
    val vote_average : Double,
    val vote_count : Int
)