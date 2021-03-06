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
import unibo.it.controls.presentation.ControlsScreen
import unibo.it.loading.presentation.LoadingScreen
import unibo.it.lookup.presentation.LookupScreen
import unibo.it.menu.presentation.MenuScreen
import unibo.it.settings.presentation.SettingsScreen


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
                onPairedClick = actions.openControls,
                onSettingsButtonClick = actions.openSettings
            )
        }

        composable(
            route = Destinations.Lookup,
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
            }) {
            LookupScreen(
                onPairedClick = actions.openMenu
            )
        }

        composable(
            route = Destinations.Controls,
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
            }) {
            ControlsScreen()
        }


        composable(
            route = Destinations.Settings,
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
            }) {
            SettingsScreen(onSavingClick = actions.openMenu)
        }

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

    val openSettings: () -> Unit = {
        navController.navigate(Destinations.Settings)
    }
}


private object Constants {
    const val TWEEN_DURATION = 700
}
