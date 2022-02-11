package unibo.it.menu.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import unibo.it.domain.repository.MenuRepository
import unibo.it.menu_api.presentation.MenuState
import unibo.it.menu_api.presentation.MenuViewModel

class MenuViewModelImpl(
    private val menuRepository: MenuRepository
) : MenuViewModel() {

    override fun isDeviceConnected(): Flow<MenuState> = flow {
        menuRepository.isDeviceConnected().collect {
            if (it) {
                emit(MenuState.Paired)
            }
        }
    }


}