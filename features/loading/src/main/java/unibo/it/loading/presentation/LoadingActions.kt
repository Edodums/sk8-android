package unibo.it.loading.presentation

internal data class LoadingActions(
    val onAuthenticated: () -> Unit,
    val onNotAuthenticated: () -> Unit
)
