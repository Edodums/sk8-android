package unibo.it.sk8

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import unibo.it.sk8.loading.Loading
import unibo.it.sk8.ui.theme.Sk8Theme

@Composable
fun SK8App() {
    Sk8Theme {
        Loading()
    }
}