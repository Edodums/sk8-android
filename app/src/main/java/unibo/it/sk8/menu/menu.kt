package unibo.it.sk8.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayCircleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavHostController
import unibo.it.sk8.R
import unibo.it.sk8.navigation.Destinations
import unibo.it.sk8.ui.common.BasicLogo
import unibo.it.sk8.ui.theme.start_skatin_container
import unibo.it.sk8.ui.theme.start_skatin_content

@Composable
fun MenuScreen(
    viewModel: MenuViewModel,
    navController: NavHostController
) {
    val menuState by viewModel.menuState.collectAsState()

    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer,
        topBar = { BasicLogo() },
        content = {
            when (menuState) {
                MenuState.NotPaired -> NotPairedScreen(viewModel, navController)
                MenuState.Paired -> StartScreen(viewModel, navController)
            }
        }
    )
}

@Composable
fun StartScreen(viewModel: MenuViewModel, navController: NavHostController) {
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
            onClick = { /*TODO*/ },
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
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(stringResource(id = R.string.start))
                        }
                        append(" ")
                        append(stringResource(id = R.string.skatin))
                    },
                    fontSize = 24.sp,
                    textAlign = TextAlign.Left
                )
            }

        )
    }
}

@Composable
fun NotPairedScreen(viewModel: MenuViewModel, navController: NavHostController) {
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
            onClick = {
                navController.navigate(Destinations.Lookup)
            },
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
