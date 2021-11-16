package com.nividata.movie_time.domain.data.model.response

interface BaseResponse {
    val status: State
    val message: String
}

enum class State {
    SUCCESS, NOT_FOUND, FAILED, UNAUTHORIZED
}
