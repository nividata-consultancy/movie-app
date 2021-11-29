package com.nividata.movie_time.view.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.nividata.movie_time.BuildConfig
import com.nividata.movie_time.R
import com.nividata.movie_time.navigation.Screen
import com.nividata.movie_time.view.base.LAUNCH_LISTEN_FOR_EFFECTS
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
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (appNameAndIcon, progressBar, versionText) = createRefs()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.constrainAs(appNameAndIcon) {
                top.linkTo(parent.top)
                bottom.linkTo(progressBar.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Card(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_playstore),
                    contentDescription = "",
                    modifier = Modifier.size(width = 64.dp, height = 64.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        CircularProgressIndicator(
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.constrainAs(progressBar) {
                bottom.linkTo(versionText.top, margin = 24.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Text(
            text = "App Version ${BuildConfig.VERSION_NAME}(${BuildConfig.VERSION_CODE})",
            style = MaterialTheme.typography.caption.copy(fontSize = 10.sp),
            modifier = Modifier.constrainAs(versionText) {
                bottom.linkTo(parent.bottom, margin = 28.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}