package com.nividata.movie_time.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.movie_time.di.ThemeManager
import com.nividata.movie_time.navigation.OwlsNavigation
import com.nividata.movie_time.ui.theme.OwlsTheme
import com.nividata.movie_time.view.movieDetails.MovieDetailsViewModel
import com.nividata.movie_time.view.movieList.MovieListViewModel
import com.nividata.movie_time.view.tvDetails.TvDetailsViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@ExperimentalAnimationApi
@InternalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var themeManager: ThemeManager

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun movieDetailViewModelFactory(): MovieDetailsViewModel.Factory
        fun tvDetailViewModelFactory(): TvDetailsViewModel.Factory
        fun movieListViewModelFactory(): MovieListViewModel.Factory
    }

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val themeType by themeManager.themeType.collectAsState()

            OwlsTheme(themeType = themeType) {
                Surface(color = MaterialTheme.colors.background) {
                    OwlsNavigation()
                }
            }
        }
    }
}
