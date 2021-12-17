package unibo.it.sk8

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import unibo.it.sk8.navigation.Nav
import unibo.it.sk8.ui.theme.Sk8Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        softInputMode()
        configureAmplify()
        fullScreen()

        setContent {
            SK8App()
        }
    }

    private fun softInputMode() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @ExperimentalCoroutinesApi
    @Composable
    fun SK8App() {
        Sk8Theme {
            Nav()
        }
    }

    private fun configureAmplify() {
        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
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
