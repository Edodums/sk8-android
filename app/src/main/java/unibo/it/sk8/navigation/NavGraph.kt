package unibo.it.sk8.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import unibo.it.common.navigation.Destinations
import unibo.it.sk8.presentation.LoadingScreen


/**
 * https://github.com/igorescodro/alkaa/blob/main/app/src/main/java/com/escodro/alkaa/navigation/NavGraph.kt
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Nav(startDestination: String = Destinations.Loading) {
    val navController = rememberAnimatedNavController()
    val context = LocalContext.current
    val actions = remember(navController) {
        Actions(navController = navController)
    }

    AnimatedNavHost(navController = navController, startDestination = startDestination) {

        composable(route = Destinations.Loading, enterTransition = { _, _ ->
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(Constants.TWEEN_DURATION)
            )
        }) {
            /*LoadingScreen(viewModel = , navController = )*/
        }
    }
}

internal data class Actions(val navController: NavHostController) {
    val openLoading: () -> Unit = {
        navController.navigate(Destinations.Loading)
    }
}


private object Constants {
    const val TWEEN_DURATION = 700
}
