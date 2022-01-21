package unibo.it.sk8.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import unibo.it.auth.presentation.AuthScreen
import unibo.it.common.navigation.Destinations
import unibo.it.loading.presentation.LoadingScreen


/**
 * https://github.com/igorescodro/alkaa/blob/main/app/src/main/java/com/escodro/alkaa/navigation/NavGraph.kt
 */
@ExperimentalCoroutinesApi
@FlowPreview
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Nav(startDestination: String = Destinations.Loading) {
    val navController = rememberAnimatedNavController()
    val actions = remember(navController) {
        Actions(navController = navController)
    }

    AnimatedNavHost(navController = navController, startDestination = startDestination) {
        composable(
            route = Destinations.Loading,
            enterTransition = { _, _ ->
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(Constants.TWEEN_DURATION)
                )
            }, exitTransition = { _, _ ->
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            }) {
            LoadingScreen(
                onLoading = actions.openMenu,
                onAuthenticated = actions.openMenu,
                onNotAuthenticated = actions.openAuthentication
            )
        }

        composable(
            route = Destinations.Authentication,
            enterTransition = { _, _ ->
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(Constants.TWEEN_DURATION)
                )
            }, exitTransition = { _, _ ->
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            }) {
            AuthScreen(
                onVerified = actions.openMenu
            )
        }

        /*composable(Destinations.Menu) {
            MenuScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }

        composable(Destinations.Lookup) {
            LookupScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }

        composable(Destinations.Controls) {
            ControlsScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }*/

    }
}

internal data class Actions(val navController: NavHostController) {
    val openLoading: () -> Unit = {
        navController.navigate(Destinations.Loading)
    }

    val openAuthentication: () -> Unit = {
        navController.navigate(Destinations.Authentication)
    }

    val openMenu: () -> Unit = {
        navController.navigate(Destinations.Menu)
    }

    val openControls: () -> Unit = {
        navController.navigate(Destinations.Controls)
    }

    val openLookup: () -> Unit = {
        navController.navigate(Destinations.Lookup)
    }
}


private object Constants {
    const val TWEEN_DURATION = 700
}
