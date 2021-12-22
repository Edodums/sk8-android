package unibo.it.sk8.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import unibo.it.sk8.auth.AuthScreen
import unibo.it.sk8.auth.AuthViewModel
import unibo.it.sk8.loading.LoadingScreen
import unibo.it.sk8.loading.LoadingViewModel
import unibo.it.sk8.lookup.LookupScreen
import unibo.it.sk8.lookup.LookupViewModel
import unibo.it.sk8.menu.MenuScreen
import unibo.it.sk8.menu.MenuViewModel

object Destinations {
    const val Loading = "loading"
    const val Authentication = "auth"
    const val Menu = "menu"
    const val Lookup = "lookup"
}

@OptIn(FlowPreview::class)
@ExperimentalCoroutinesApi
@Composable
fun Nav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.Loading) {
        composable(Destinations.Loading) {
            LoadingScreen(
                viewModel = hiltViewModel<LoadingViewModel>(),
                navController = navController
            )
        }

        composable(Destinations.Authentication) {
            AuthScreen(
                viewModel = hiltViewModel<AuthViewModel>(),
                navController = navController
            )
        }

        composable(Destinations.Menu) {
            MenuScreen(
                viewModel = hiltViewModel<MenuViewModel>(),
                navController = navController
            )
        }

        composable(Destinations.Lookup) {
            LookupScreen(
                viewModel = hiltViewModel<LookupViewModel>(),
                navController = navController
            )
        }
    }
}
