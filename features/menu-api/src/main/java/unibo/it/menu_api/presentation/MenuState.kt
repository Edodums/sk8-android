package unibo.it.menu_api.presentation

sealed class MenuState(
    val hasPaired: Boolean = false
) {
    object NotPaired : MenuState()
    object Paired : MenuState(hasPaired = true)
}