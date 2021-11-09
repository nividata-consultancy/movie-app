package com.nividata.owls.view

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
import com.nividata.owls.di.ThemeManager
import com.nividata.owls.navigation.OwlsNavigation
import com.nividata.owls.ui.theme.OwlsTheme
import com.nividata.owls.view.movieDetails.MovieDetailsViewModel
import com.nividata.owls.view.movieList.MovieListViewModel
import com.nividata.owls.view.tvDetails.TvDetailsViewModel
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
