package unibo.it.sk8.lookup

import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import unibo.it.sk8.ui.common.BasicLogo

@Composable
fun LookupScreen(
    viewModel: LookupViewModel,
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer,
        topBar = { BasicLogo() },
        content = {
            when (uiState) {
                LookupState.Action -> ActionScreen()
                LookupState.Found -> FoundScreen(navController)
                LookupState.Loading -> LoadingScreen()
            }
        }
    )
}

@Composable
fun ActionScreen() {

}

@Composable
fun FoundScreen(navController: NavHostController) {

}

@Composable
fun LoadingScreen() {

}
