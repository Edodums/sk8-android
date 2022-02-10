package unibo.it.lookup_api.presentation

sealed class ScanState {
    object Stopped : ScanState()
    object Scanning : ScanState()
    data class Failed(val message: CharSequence) : ScanState()
}