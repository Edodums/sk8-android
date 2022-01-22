<<<<<<<< HEAD:features/menu-api/src/main/java/unibo/it/menu_api/presentation/MenuState.kt
package unibo.it.menu_api.presentation
========
package unibo.it.sk8.menu
>>>>>>>> main:app/src/main/java/unibo/it/sk8/menu/MenuState.kt

sealed class MenuState(
    val hasPaired: Boolean = false
) {
    object NotPaired : MenuState()
    object Paired : MenuState(hasPaired = true)
<<<<<<<< HEAD:features/menu-api/src/main/java/unibo/it/menu_api/presentation/MenuState.kt
}
========
}

>>>>>>>> main:app/src/main/java/unibo/it/sk8/menu/MenuState.kt
