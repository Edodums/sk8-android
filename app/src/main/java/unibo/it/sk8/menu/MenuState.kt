package unibo.it.sk8.menu

sealed class MenuState(
    val hasPaired: Boolean = false
) {
    object NotPaired : MenuState()
    object Paired : MenuState(hasPaired = true)
}

