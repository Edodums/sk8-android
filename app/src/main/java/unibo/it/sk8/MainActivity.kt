package unibo.it.sk8

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.amplifyframework.AmplifyException
import com.amplifyframework.core.Amplify
import dagger.hilt.android.AndroidEntryPoint
import unibo.it.sk8.navigation.NavigationManager
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import unibo.it.sk8.navigation.Nav
import unibo.it.sk8.ui.theme.Sk8Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationManager: NavigationManager

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureAmplify()
        fullScreen()

        setContent {
            val navController = rememberNavController()
            navigationManager.commands.collectAsState().value.also { command ->
                if (command.destination.isNotEmpty()) {
                    navController.navigate(command.destination)
                }
            }

            SK8App()
        }
    }

    @ExperimentalCoroutinesApi
    @Composable
    fun SK8App() {
        Sk8Theme {
            Nav(navController = rememberNavController())
        }
    }

    private fun configureAmplify() {
        try {
            Amplify.configure(applicationContext)
            Log.i(APP_NAME, "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e(APP_NAME, "Could not initialize Amplify", error)
        }
    }

    private fun fullScreen() {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        actionBar?.hide()

        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    companion object {
        const val APP_NAME = "SK8"
    }
}
