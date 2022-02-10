package unibo.it.sk8

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import unibo.it.sk8.navigation.Nav
import unibo.it.sk8.ui.theme.Sk8Theme


@FlowPreview
@RequiresApi(Build.VERSION_CODES.S)
class MainActivity : ComponentActivity() {
    private val permissions = listOf(
        Manifest.permission.BLUETOOTH_SCAN,
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.BLUETOOTH_ADMIN,
        Manifest.permission.BLUETOOTH_ADVERTISE,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        softInputMode()
        configureAmplify()
        fullScreen()
        askBLEPermissions(permissions)

        if (!everythingIsGranted(permissions)) {
            // TODO: check if there's a better way to do this
            askBLEPermissions(permissions)
        }

        verifyBluetoothCapabilities()

        setContent {
            SK8App()
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun verifyBluetoothCapabilities() {
        val bluetoothAdapter = (getSystemService(BLUETOOTH_SERVICE) as BluetoothManager).adapter

        when {
            bluetoothAdapter == null ->
                // Bluetooth is not supported on this hardware platform
                showErrorText("onCreate: bluetooth not supported")
            !bluetoothAdapter.isEnabled -> // Bluetooth is OFF, user should turn it ON
                // Prompt the use to allow the app to turn on Bluetooth
                launchIntent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        }
    }


    private fun askBLEPermissions(permissions: List<String>) {
        requestPermissions(permissions.filter { permission ->
            ActivityCompat.checkSelfPermission(
                applicationContext,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        }.toList().toTypedArray(), 55001)
    }


    private fun everythingIsGranted(permissions: List<String>): Boolean {
        return permissions.all { permission ->
            ActivityCompat.checkSelfPermission(
                applicationContext,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                Log.i("BLUETOOTH DATA", data.toString())
            }
        }

    private fun launchIntent(intentName: String) {
        resultLauncher.launch(Intent(intentName))
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

    private fun softInputMode() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    private fun showErrorText(msg: String) {
        Log.d(BLUETOOTH_TAG, "showErrorText: $msg")
    }

    companion object {
        private const val BLUETOOTH_TAG = "BLUETOOTH ERROR"
        private const val APP_NAME = "SK8"
    }
}
