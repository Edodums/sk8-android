package unibo.it.sk8.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import unibo.it.sk8.R

private const val SplashWaitTime: Long = 2000

@Composable
fun Loading() {
    val padding = 16.dp
    val bottomBarPadding = 64.dp
    val fontTitleSize = 36.sp
    val fontSubTitleSize = 16.sp

    LaunchedEffect(Unit) {
        delay(SplashWaitTime)
    }

    Scaffold(
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val scale = 2f

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Sk8 Logo",
                    modifier = Modifier.scale(scale)
                )

                Spacer(modifier = Modifier.padding(padding))

                Text(
                    text = stringResource(id = R.string.loading_title),
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.W700,
                    fontSize = fontTitleSize,
                    textAlign = TextAlign.Center
                )
            }
        },
        bottomBar = {
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
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}