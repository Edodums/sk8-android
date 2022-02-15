package unibo.it.menu.presentation

internal data class MenuActions(
    val onNotPairedClick: () -> Unit,
    val onPairedClick: () -> Unit,
    val onSettingsButtonClick: () -> Unit
)