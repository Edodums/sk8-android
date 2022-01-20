package unibo.it.loading.presentation

internal data class LoadingActions(
    val onLoading: () -> Unit,
    val onAuthenticated: () -> Unit,
    val onNotAuthenticated: () -> Unit
)
