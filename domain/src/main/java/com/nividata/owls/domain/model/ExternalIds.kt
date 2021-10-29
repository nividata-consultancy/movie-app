package com.nividata.owls.domain.model

data class ExternalIds(
    val id: Int,
    val imdb_id: String,
    val facebook_id: String?,
    val instagram_id: String?,
    val twitter_id: String?,
    val freebase_mid: String?,
    val freebase_id: String?,
    val tvdb_id: Int?,
    val tvrage_id: String?,
)
