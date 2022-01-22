package unibo.it.sk8.navigation

import androidx.compose.runtime.Composable
<<<<<<< HEAD
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
import unibo.it.menu.presentation.MenuScreen


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
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(Constants.TWEEN_DURATION)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(Constants.TWEEN_DURATION)
                )
            }
        ) {
            LoadingScreen(
                onLoading = actions.openMenu,
                onAuthenticated = actions.openMenu,
                onNotAuthenticated = actions.openAuthentication
            )
        }

        composable(
            route = Destinations.Authentication,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(Constants.TWEEN_DURATION)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(Constants.TWEEN_DURATION)
                )
            }
        ) {
            AuthScreen(
                onVerified = actions.openMenu
            )
        }

        composable(
            route = Destinations.Menu,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(Constants.TWEEN_DURATION)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(Constants.TWEEN_DURATION)
                )
            }
        ) {
            MenuScreen(
                onNotPairedClick = actions.openLookup,
                onPairedClick = actions.openControls
            )
        }

        /*
=======
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import unibo.it.sk8.auth.AuthScreen
import unibo.it.sk8.controls.ControlsScreen
import unibo.it.sk8.loading.LoadingScreen
import unibo.it.sk8.lookup.LookupScreen
import unibo.it.sk8.menu.MenuScreen

object Destinations {
    const val Loading = "loading"
    const val Authentication = "auth"
    const val Menu = "menu"
    const val Lookup = "lookup"
    const val Controls = "controls"
}

@OptIn(FlowPreview::class)
@ExperimentalCoroutinesApi
@Composable
fun Nav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.Loading) {
        composable(Destinations.Loading) {
            LoadingScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }

        composable(Destinations.Authentication) {
            AuthScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }

        composable(Destinations.Menu) {
            MenuScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }

>>>>>>> main
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
<<<<<<< HEAD
        }*/

    }
}

internal data class Actions(val navController: NavHostController) {
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
=======
        }
    }
}
>>>>>>> main
