package unibo.it.sk8.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import unibo.it.sk8.auth.Auth
import unibo.it.sk8.auth.AuthViewModel
import unibo.it.sk8.loading.Loading
import unibo.it.sk8.loading.LoadingViewModel
import unibo.it.sk8.menu.Menu
import unibo.it.sk8.menu.MenuViewModel

object Destinations {
    val Default = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = ""

    }

    val loading = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = "loading"

    }

    val authentication = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = "auth"

    }

    val menu = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = "menu"
    }

}

@ExperimentalCoroutinesApi
@Composable
fun Nav(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destinations.loading.destination) {
        composable(Destinations.loading.destination) {
            Loading(
                viewModel = hiltViewModel<LoadingViewModel>()
            )
        }

        composable(Destinations.authentication.destination) {
            Auth(
                viewModel = hiltViewModel<AuthViewModel>()
            )
        }

        composable(Destinations.menu.destination) {
            Menu(
                viewModel = hiltViewModel<MenuViewModel>()
            )
        }
    }
}
