package unibo.it.lookup_api.presentation

sealed class BluetoothState(
    val isEnabled: Boolean = false
) {
    object Disabled : BluetoothState()
    object Enabled : BluetoothState(isEnabled = true)
}
