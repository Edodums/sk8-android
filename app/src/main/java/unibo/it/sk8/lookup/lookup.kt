package unibo.it.sk8.lookup

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.valentinilk.shimmer.shimmer
import unibo.it.sk8.R
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.click_circle_description),
            fontSize = 30.sp,
            lineHeight = 44.sp,
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(bottom = 150.dp))

        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.circles),
                contentDescription = "Circles around the bluetooth circle",
                modifier = Modifier
                    .scale(2f)
                    .zIndex(2f)
            )

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(100.dp)
                    .zIndex(3f),  //avoid the oval shape
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Icon(
                    Icons.Filled.Bluetooth,
                    contentDescription = "Bluetooth click button",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun FoundScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.click_circle_description),
            fontSize = 30.sp,
            lineHeight = 44.sp,
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.looking_for),
            fontSize = 30.sp,
            lineHeight = 44.sp,
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(bottom = 120.dp))

        Box(
            contentAlignment = Alignment.Center
        ) {
            val infiniteTransition = rememberInfiniteTransition()
            val angle by infiniteTransition.animateFloat(
                initialValue = 0F,
                targetValue = 360F,
                animationSpec = infiniteRepeatable(
                    animation = tween(1800, easing = LinearEasing)
                )
            )

            val scaleImage = 1.8f
            val zIndex = 2f

            Image(
                painter = painterResource(id = R.drawable.circles),
                contentDescription = "Circles around the bluetooth circle",
                modifier = Modifier
                    .scale(scaleImage)
                    .zIndex(zIndex)
            )

            Image(
                painter = painterResource(id = R.drawable.skate_lookup),
                contentDescription = "Circles around the bluetooth circle",
                modifier = Modifier
                    .scale(scaleImage)
                    .zIndex(zIndex)
                    .graphicsLayer {
                        rotationZ = angle
                    }
            )
        }

        Spacer(modifier = Modifier.padding(bottom = 84.dp))

        LazyColumn(Modifier.shimmer()) {
            items(3) {
                Box(
                    modifier = Modifier
                        .width(280.dp)
                        .height(100.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.LightGray)
                )
            }
        }

    }

}
