package com.nividata.movie_time.view.movieList

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nividata.movie_time.domain.core.repository.MovieTimeRepository
import com.nividata.movie_time.domain.core.repository.TmdbRepository
import com.nividata.movie_time.domain.model.Movie
import kotlinx.coroutines.delay

class MovieSource(
    private val tmdbRepository: TmdbRepository,
    private val movieTimeRepository: MovieTimeRepository,
    private val id: Int?,
    private val type: String?,
    private val categoryType: String,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val homeMovieList =
            if (id == null)
                movieTimeRepository.getMovieList(
                    page = params.key ?: 1,
                    pageSize = 10,
                    categoryType = categoryType,
                )
            else
                tmdbRepository.getMovieList(
                    id = id,
                    page = params.key ?: 1,
                    pageSize = 20,
                    type = type!!,
                    categoryType = categoryType,
                )
        delay(1000)
        return LoadResult.Page(
            data = homeMovieList.movieList,
            nextKey = if (homeMovieList.totalPages!! > homeMovieList.page!!) homeMovieList.page!! + 1 else null,
            prevKey = null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override val keyReuseSupported: Boolean
        get() = true
}