package com.nividata.owls.view.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.nividata.owls.domain.core.view.ViewState
import com.nividata.owls.navigation.NOTY_NAV_HOST_ROUTE
import com.nividata.owls.navigation.Screen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun SplashView(
    navController: NavHostController,
    viewModel: SplashViewModel
) {
    val viewState = viewModel.authFlow.collectAsState(initial = null).value

    when (viewState) {
//        is ViewState.Loading -> LoaderDialog()
        is ViewState.Success -> {
            navController.navigate(
                route = Screen.NewMain.route,
                builder = {
                    launchSingleTop = true
                    popUpTo(NOTY_NAV_HOST_ROUTE) { inclusive = true }
                }
            )
        }
//        is ViewState.Failed -> FailureDialog(viewState.message)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = MaterialTheme.colors.secondary)
    }
}