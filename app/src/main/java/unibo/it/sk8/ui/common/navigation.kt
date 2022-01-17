package unibo.it.sk8.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import unibo.it.sk8.utils.Constant

@Composable
fun Navigate(navController: NavHostController, destination: String) {
    LaunchedEffect(Unit) {
        delay(Constant.NAVIGATION_DELAY)
        navController.navigate(destination)
    }
}
