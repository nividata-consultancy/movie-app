package com.nividata.owls.domain.model

import com.squareup.moshi.Json

data class WatchProviders(
    val id: Int,
    val results: Results
) {
    data class Results(
        @Json(name = "IN") val iN: IN?
    ) {
        data class IN(
            val link: String,
            val rent: List<Flatrate>?,
            val flatrate: List<Flatrate>?,
            val buy: List<Flatrate>?
        ) {
            data class Flatrate(
                val display_priority: Int,
                val logo_path: String,
                val provider_id: Int,
                val provider_name: String
            )
        }
    }
}