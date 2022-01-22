package unibo.it.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import unibo.it.common.R

@Composable
fun BasicLogo() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Sk8 Logo icon",
        modifier = Modifier
            .padding(
                all = 32.dp
            )
            .scale(0.8f)
    )
}

@Composable
fun LoadingLogo() {
    val scale = 2f

    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Sk8 Logo",
        modifier = Modifier.scale(scale)
    )
}


@Composable
fun AuthLogo() {
    val horizontalPadding = 48.dp
    val verticalPadding = 64.dp
    var logo = R.drawable.logo_auth_white

    if (!isSystemInDarkTheme()) {
        logo = R.drawable.logo
    }

    Image(
        painter = painterResource(id = logo),
        contentDescription = "Sk8 Logo White",
        modifier = Modifier.padding(
            horizontal = horizontalPadding,
            vertical = verticalPadding
        )
    )
}
