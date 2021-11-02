package com.nividata.owls.view.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nividata.owls.domain.core.repository.OwlsRepository
import com.nividata.owls.domain.core.repository.TmdbRepository
import com.nividata.owls.domain.core.session.SessionManager
import com.nividata.owls.domain.model.Movie
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class MovieListViewModel @AssistedInject constructor(
    tmdbRepository: TmdbRepository,
    owlsRepository: OwlsRepository,
    private val sessionManager: SessionManager,
    @Assisted private val id: Int?,
    @Assisted("type") private val type: String?,
    @Assisted("categoryName") private val categoryName: String,
    @Assisted("categoryType") private val categoryType: String,
) : /*BaseViewModel<MovieListContract.Event,
        MovieListContract.State,
        MovieListContract.Effect>()*/ViewModel() {

    private var movieSource: MovieSource =
        MovieSource(
            tmdbRepository,
            owlsRepository,
            id = id,
            type = type,
            categoryType = categoryType
        )

    val movies: Flow<PagingData<Movie>> =
        Pager(PagingConfig(pageSize = if (id == null) 10 else 20)) {
            movieSource
        }.flow.cachedIn(
            viewModelScope
        )

//    override fun setInitialState(): MovieListContract.State = MovieListContract.State.Loading
//
//    override fun handleEvents(event: MovieListContract.Event) {
//        when (event) {
//            is MovieListContract.Event.MovieSelection -> {
//                if (event.type == "movie") {
//                    setEffect {
//                        MovieListContract.Effect.Navigation.ToMovieDetails(event.id)
//                    }
//                } else {
//                    setEffect {
//                        MovieListContract.Effect.Navigation.ToTvDetails(event.id)
//                    }
//                }
//            }
//        }
//    }

//    fun getMovieList(): Flow<PagingData<Movie>> {
//        return Pager(
//            PagingConfig(
//                pageSize = 21,
//                initialLoadSize = 21,
//                enablePlaceholders = false,
//            )
//        ) { movieSource }.flow.cachedIn(
//            viewModelScope
//        )
//    }

    @AssistedFactory
    interface Factory {
        fun create(
            id: Int?,
            @Assisted("type") type: String?,
            @Assisted("categoryName") categoryName: String,
            @Assisted("categoryType") categoryType: String,
        ): MovieListViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            id: Int?,
            type: String?,
            categoryName: String,
            categoryType: String,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(
                    id = id,
                    type = type,
                    categoryName = categoryName,
                    categoryType = categoryType
                ) as T
            }
        }
    }
}

@Module
@InstallIn(ActivityRetainedComponent::class)
interface AssistedInjectModule