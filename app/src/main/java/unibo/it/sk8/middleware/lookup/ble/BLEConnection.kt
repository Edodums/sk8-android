package unibo.it.lookup.ble

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast


class BLEConnection {
    private fun PackageManager.missingSystemFeature(name: String): Boolean = !hasSystemFeature(name)

    fun checkBT(packageManager: PackageManager, context: Context) {
        // Check to see if the Bluetooth classic feature is available.
        checkBTFeature(
            packageManager,
            context,
            PackageManager.FEATURE_BLUETOOTH,
            R.string.bluetooth_not_supported
        )
    }

    fun checkBLE(packageManager: PackageManager, context: Context) {
        // Check to see if the BLE feature is available.
        checkBTFeature(
            packageManager,
            context,
            PackageManager.FEATURE_BLUETOOTH_LE,
            R.string.ble_not_supported
        )
    }

    private fun checkBTFeature(
        packageManager: PackageManager,
        context: Context,
        missingFeatureName: String,
        toastTextId: Int
    ) {
        packageManager.takeIf { it.missingSystemFeature(missingFeatureName) }
            ?.also {
                Toast.makeText(context, toastTextId, Toast.LENGTH_SHORT).show()
            }
    }

    /*
    private val bluetoothManager
    private val bluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner
    private var scanning = false
    private val handler = Handler(Looper.myLooper()!!)

    // TODO: on Monday 03/01/2022 I'll finish it
    private val leDeviceListAdapter = LeDeviceListAdapter()
    // Device scan callback.
    private val leScanCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            leDeviceListAdapter.addDevice(result.device)
            leDeviceListAdapter.notifyDataSetChanged()
        }
    }

    private fun scanLeDevice() {
        if (!scanning) { // Stops scanning after a pre-defined scan period.
            handler.postDelayed({
                scanning = false
                bluetoothLeScanner.stopScan(leScanCallback)
            }, SCAN_PERIOD)
            scanning = true
            bluetoothLeScanner.startScan(leScanCallback)
        } else {
            scanning = false
            bluetoothLeScanner.stopScan(leScanCallback)
        }
    }
*/
    companion object {
        // Stops scanning after 10 seconds.
        private const val SCAN_PERIOD: Long = 10000
    }
}
