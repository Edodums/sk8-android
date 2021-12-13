package unibo.it.sk8.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {
    var commands = MutableStateFlow(Destinations.Default)

    fun navigate(
        directions: NavigationCommand
    ) {
        commands.value = directions
    }
}