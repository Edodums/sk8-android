package unibo.it.lookup.ble

sealed class BluetoothState(
    val isEnabled: Boolean = false
) {
    object Disabled : BluetoothState()
    object Enabled : BluetoothState(isEnabled = true)
}
