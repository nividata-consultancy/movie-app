package com.nividata.owls.view.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.nividata.owls.navigation.Screen
import com.nividata.owls.view.base.LAUNCH_LISTEN_FOR_EFFECTS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.collect

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@Composable
fun SplashView(
    navController: NavHostController,
    viewModel: SplashViewModel
) {
    val state = viewModel.viewState.value

    val onMainNavigation: () -> Unit = {
        navController.navigate(Screen.Main.route)
//        navController.navigate(Screen.TvDetail.route(71446))
//        navController.navigate(Screen.MovieDetail.route(76341))
    }

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is SplashContract.Effect.Navigation.ToMain -> onMainNavigation()
            }
        }.collect()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            is SplashContract.State.Loading -> CircularProgressIndicator(color = MaterialTheme.colors.secondary)
            is SplashContract.State.Success -> {
                CircularProgressIndicator(color = MaterialTheme.colors.secondary)
            }
            is SplashContract.State.Failed -> Text(text = state.message)
        }

    }
}