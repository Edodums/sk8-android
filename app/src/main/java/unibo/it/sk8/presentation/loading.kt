package unibo.it.sk8.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import unibo.it.sk8.R
import unibo.it.sk8.navigation.Destinations
import unibo.it.sk8.ui.common.LoadingLogo
import unibo.it.sk8.ui.common.Navigate

@Composable
fun LoadingScreen(
    viewModel: LoadingViewModel,
    navController: NavHostController
) {
    val state by viewModel.uiState.collectAsState()
    Loading(
        viewState = state,
        navController = navController
    )
}

@Composable
private fun Loading(
    viewState: UserState,
    navController: NavHostController
) {
    when (viewState) {
        UserState.Loading -> {
            Loading(navController)
        }
        UserState.Authenticated -> {
            Navigate(
                navController = navController,
                destination = Destinations.Menu
            )
        }
        UserState.NotAuthenticated -> {
            Navigate(
                navController = navController,
                destination = Destinations.Authentication
            )
        }
    }
}

@Composable
private fun Loading(navController: NavHostController) {
    Navigate(
        navController = navController,
        destination = Destinations.Menu
    )

    Scaffold(
        content = { Content() },
        bottomBar = { BottomText() }
    )
}

@Composable
fun Content() {
    val padding = 16.dp
    val fontTitleSize = 36.sp

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoadingLogo()
        Spacer(modifier = Modifier.padding(padding))
        Text(
            text = stringResource(id = R.string.loading_title),
            style = MaterialTheme.typography.h1,
            fontWeight = FontWeight.Bold,
            fontSize = fontTitleSize,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BottomText() {
    val bottomBarPadding = 64.dp
    val fontSubTitleSize = 16.sp

    Box(
        Modifier
            .fillMaxWidth()
            .paddingFromBaseline(bottom = bottomBarPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.loading_subtitle),
            style = MaterialTheme.typography.h2,
            fontSize = fontSubTitleSize,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}
