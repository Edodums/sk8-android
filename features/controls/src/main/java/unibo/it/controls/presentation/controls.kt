package unibo.it.controls.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import unibo.it.common.ui.BasicLogo
import unibo.it.controls.R
import unibo.it.controls_api.presentation.ControlsViewModel
import kotlin.math.roundToInt


@Composable
fun ControlsScreen(
    viewModel: ControlsViewModel = getViewModel()
) {
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer,
        topBar = { BasicLogo() },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.controls_text),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(32.dp))
                Skate()
            }
        }
    )
}


@Composable
fun Skate() {
    val offsetFix = 60
    val min = 0.dp
    val max = 70.dp
    val padding = 20.dp
    val width = 120.dp
    val height = 400.dp
    val density = LocalDensity.current
    var offsetY by remember { mutableStateOf(0f) } // probably gonna make a percentage conversion
    val (minPx, maxPx) = with(LocalDensity.current) { min.toPx() to max.toPx() }

    Surface(
        shape = RoundedCornerShape(60.dp),
        elevation = 12.dp,
        modifier = Modifier
            .width(width)
            .height(height)
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    val newValue = offsetY + delta
                    offsetY = newValue.coerceIn(minPx, maxPx)
                }
            )
            .drawWithContent {
                val paddingPx = with(density) { padding.toPx() }
                clipRect(
                    left = -paddingPx,
                    top = 0f,
                    right = size.width + paddingPx,
                    bottom = size.height + paddingPx
                ) {
                    this@drawWithContent.drawContent()
                }
            }
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.offset { IntOffset(0, offsetY.roundToInt() - offsetFix) },
                painter = painterResource(id = R.drawable.skate_controls),
                contentDescription = "Sk8 Controls"
            )
        }
    }
}
