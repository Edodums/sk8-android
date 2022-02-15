package unibo.it.menu.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayCircleOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import unibo.it.common.ui.BasicLogo
import unibo.it.menu.R
import unibo.it.menu.presentation.ui.theme.start_skatin_container
import unibo.it.menu.presentation.ui.theme.start_skatin_content
import unibo.it.menu_api.presentation.MenuState
import unibo.it.menu_api.presentation.MenuViewModel

@Composable
fun MenuScreen(
    viewModel: MenuViewModel = getViewModel(),
    onNotPairedClick: () -> Unit,
    onPairedClick: () -> Unit,
    onSettingsButtonClick: () -> Unit,
) {
    val menuState by remember(viewModel) { viewModel }.menuState
        .collectAsState(MenuState.NotPaired)

    val actions = MenuActions(
        onNotPairedClick = onNotPairedClick,
        onPairedClick = onPairedClick,
        onSettingsButtonClick = onSettingsButtonClick
    )

    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer,
        topBar = {
            when (menuState) {
                MenuState.NotPaired -> BasicLogo()
                MenuState.Paired -> {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(200.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        BasicLogo()
                        IconButton(onClick = {
                            actions.onSettingsButtonClick()
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = "Settings screen",
                                tint = Color.Black,
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }
                }
            }
        },
        content = {
            when (menuState) {
                MenuState.NotPaired -> NotPairedScreen(onNotPairedClick = actions.onNotPairedClick)
                MenuState.Paired -> StartScreen(onPairedClick = actions.onPairedClick)
            }
        }
    )
}

@Composable
fun StartScreen(onPairedClick: () -> Unit) {
    Column(
        Modifier
            .padding(vertical = 32.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val width = 300.dp
        val shape = GenericShape { it, _ ->
            val w = it.width
            val h = it.height
            relativeMoveTo(0f, 0f)
            relativeLineTo(45f, h + 40f)
            relativeLineTo(w, -40f)
            relativeLineTo(0f, -h)
        }

        Text(
            text = stringResource(id = R.string.are_you_ready),
            fontWeight = FontWeight.Bold,
            fontSize = 42.sp,
            textAlign = TextAlign.Left,
            lineHeight = 60.sp,
            modifier = Modifier.width(width)
        )
        Spacer(modifier = Modifier.padding(all = 32.dp))
        Button(
            onClick = onPairedClick,
            modifier = Modifier.width(width),
            shape = shape,
            colors = ButtonDefaults.buttonColors(
                contentColor = start_skatin_content,
                containerColor = start_skatin_container
            ),
            content = {
                Icon(
                    imageVector = Icons.Outlined.PlayCircleOutline,
                    contentDescription = "Play",
                    modifier = Modifier
                        .padding(4.dp)
                        .width(50.dp)
                        .height(50.dp),
                    tint = Color.White
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(stringResource(id = R.string.start))
                        }
                        append(" ")
                        append(stringResource(id = R.string.skatin))
                    }, /* Remember that if displays the wrong text just do a Build -> recompile string.xml (you need to open a string.xml before) */
                    fontSize = 24.sp,
                    textAlign = TextAlign.Left
                )
            }
        )
    }
}

@Composable
fun NotPairedScreen(onNotPairedClick: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.not_paired),
            fontWeight = FontWeight.Bold,
            fontSize = 42.sp,
            textAlign = TextAlign.Center,
            lineHeight = 60.sp,
            modifier = Modifier.width(300.dp)
        )
        Spacer(modifier = Modifier.padding(all = 32.dp))
        Button(
            onClick = onNotPairedClick,
            modifier = Modifier.width(300.dp),
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onBackground,
                containerColor = MaterialTheme.colorScheme.onPrimary
            ),
            contentPadding = PaddingValues(vertical = 32.dp),
            content = {
                Text(
                    text = stringResource(id = R.string.look_up_for_one),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        )
    }
}
