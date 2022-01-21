package unibo.it.loading.presentation

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import unibo.it.common.ui.LoadingLogo
import unibo.it.loading.R
import unibo.it.loading_api.presentation.LoadingViewModel
import unibo.it.loading_api.presentation.UserState

@Composable
fun LoadingScreen(
    viewModel: LoadingViewModel = getViewModel(),
    onLoading: () -> Unit,
    onAuthenticated: () -> Unit,
    onNotAuthenticated: () -> Unit
) {
    val userState by remember(viewModel) { viewModel }.loadUserState()
        .collectAsState(UserState.Loading)

    Loading(
        viewState = userState,
        LoadingActions(
            onLoading = onLoading,
            onAuthenticated = onAuthenticated,
            onNotAuthenticated = onNotAuthenticated
        )
    )
}

@Composable
private fun Loading(
    viewState: UserState,
    loadingActions: LoadingActions
) {
    when (viewState) {
        UserState.Loading -> {
            Loading(onLoading = loadingActions.onLoading)
        }
        UserState.Authenticated ->
            loadingActions.onAuthenticated()

        UserState.NotAuthenticated ->
            loadingActions.onNotAuthenticated()
    }
}

@Composable
private fun Loading(onLoading: () -> Unit) {
    onLoading()
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
