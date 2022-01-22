package unibo.it.auth.presentation

internal data class AuthActions(
    val onVerified: () -> Unit
)
