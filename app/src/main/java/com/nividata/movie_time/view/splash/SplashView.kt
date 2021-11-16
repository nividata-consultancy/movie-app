package com.nividata.movie_time.view.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.nividata.movie_time.navigation.Screen
import com.nividata.movie_time.view.base.LAUNCH_LISTEN_FOR_EFFECTS
import com.nividata.movie_time.view.common.ErrorMessageView
import com.nividata.movie_time.view.common.ProgressView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@Composable
fun SplashView(
    navController: NavHostController,
    viewModel: SplashViewModel
) {
    val state = viewModel.viewState.value

    val onMainNavigation: () -> Unit = {
        navController.navigate(Screen.Main.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
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

    when (state) {
        is SplashContract.State.Loading -> ProgressView()
        is SplashContract.State.Success -> {
            ProgressView()
        }
        is SplashContract.State.Failed -> ErrorMessageView(message = state.message)
    }
}