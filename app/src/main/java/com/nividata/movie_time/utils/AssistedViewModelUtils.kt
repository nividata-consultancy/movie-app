package com.nividata.movie_time.utils

import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.nividata.movie_time.view.MainActivity
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Composable
inline fun <reified VM : ViewModel> assistedViewModel(
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    provideFactory: MainActivity.ViewModelFactoryProvider.() -> ViewModelProvider.Factory,
): VM {
    val factory = provideFactory(assistedViewModelFactory())
    return viewModel(viewModelStoreOwner, factory = factory)
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Composable
fun assistedViewModelFactory() = EntryPointAccessors.fromActivity(
    LocalContext.current as Activity,
    MainActivity.ViewModelFactoryProvider::class.java
)
