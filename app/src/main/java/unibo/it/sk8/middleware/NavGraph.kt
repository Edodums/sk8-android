/*
package unibo.it.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import unibo.it.lookup.LookupScreen
import unibo.it.auth.AuthScreen
import unibo.it.controls.ControlsScreen
import unibo.it.loading.LoadingScreen
import unibo.it.menu.MenuScreen

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
        }
    }
}
*/
