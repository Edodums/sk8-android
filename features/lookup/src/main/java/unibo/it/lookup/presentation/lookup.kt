package unibo.it.lookup.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.DoneOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.getViewModel
import unibo.it.common.ui.BasicLogo
import unibo.it.lookup.R
import unibo.it.lookup.presentation.ui.theme.lookup_device_chosen_box_primary_container
import unibo.it.lookup.presentation.ui.theme.lookup_found_primary
import unibo.it.lookup_api.presentation.LookupState
import unibo.it.lookup_api.presentation.LookupViewModel

@Composable
fun LookupScreen(
    viewModel: LookupViewModel = getViewModel()
) {
    val lookupState by viewModel.lookupState.collectAsState(LookupState.Action)

    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer,
        topBar = { BasicLogo() },
        content = {
            when (lookupState) {
                LookupState.Action -> ActionScreen(viewModel)
                LookupState.Loading -> LoadingScreen()
                LookupState.Found -> FoundScreen(viewModel)
            }
        }
    )
}

@Composable
fun ActionScreen(viewModel: LookupViewModel) {
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
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.circles),
                contentDescription = "Circles around the bluetooth circle",
                modifier = Modifier
                    .scale(2f)
                    .zIndex(2f)
            )
            Button(
                onClick = {
                    viewModel.changeState(LookupState.Loading)
                    viewModel.handleScan()
                },
                modifier = Modifier
                    .size(100.dp)
                    .zIndex(3f),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
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
fun FoundScreen(viewModel: LookupViewModel) {
    val advertisements = viewModel.advertisements.collectAsState().value
    val chosenDeviceName = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.these_ones),
            fontSize = 28.sp,
            lineHeight = 42.sp,
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(4.dp))
        ClickableText(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        textDecoration = TextDecoration.Underline, fontSize = 20.sp,
                        color = lookup_found_primary
                    )
                ) {
                    append(stringResource(id = R.string.scan_again))
                }
            },
            onClick = {
                viewModel.changeState(LookupState.Loading)
            }
        )
        Spacer(modifier = Modifier.padding(24.dp))
        LazyColumn {
            advertisements.forEach { advertisement ->
                item {
                    val locallySelected = remember { mutableStateOf(false) }
                    val modifier = Modifier
                        .width(280.dp)
                        .height(100.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            color = when {
                                locallySelected.value -> {
                                    lookup_device_chosen_box_primary_container
                                }
                                else -> {
                                    Color.LightGray
                                }
                            }
                        )
                    Box(
                        modifier = modifier.clickable {
                            locallySelected.value = !locallySelected.value
                            // TODO: handle the case of empty text
                            chosenDeviceName.value = advertisement.name.toString()
                        },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        advertisement.name?.let { name ->
                            when {
                                locallySelected.value -> {
                                    Row(Modifier.padding(18.dp)) {
                                        Text(
                                            text = name,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.padding(4.dp))
                                        Icon(
                                            Icons.Filled.DoneOutline,
                                            contentDescription = "Chosen bluetooth device",
                                            tint = Color.White
                                        )
                                    }
                                }
                                else -> {
                                    Text(
                                        text = name,
                                        color = Color.Black,
                                        modifier = Modifier.padding(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        Button(
            onClick = { viewModel.setDeviceByName(chosenDeviceName.value) },
            enabled = chosenDeviceName.value.isNotBlank(),
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = lookup_found_primary,
                disabledContainerColor = MaterialTheme.colorScheme.background
            ),
            contentPadding = PaddingValues(12.dp),
            content = {
                Text(
                    text = stringResource(id = R.string.add_device),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.displayMedium,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            }
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
        Box(contentAlignment = Alignment.Center) {
            val zIndex = 2f
            val scaleImage = 1.8f
            val infiniteTransition = rememberInfiniteTransition()
            val angle by infiniteTransition.animateFloat(
                initialValue = 0F,
                targetValue = 360F,
                animationSpec = infiniteRepeatable(
                    animation = tween(1800, easing = LinearEasing)
                )
            )

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
