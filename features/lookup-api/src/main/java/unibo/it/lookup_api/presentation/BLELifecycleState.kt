package unibo.it.lookup_api.presentation

enum class BLELifecycleState {
    Disconnected,
    Scanning,
    Connecting,
    ConnectedDiscovering,
    ConnectedSubscribing,
    Connected
}