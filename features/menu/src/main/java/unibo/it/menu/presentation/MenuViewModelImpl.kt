package unibo.it.menu.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import unibo.it.domain.repository.MenuRepository
import unibo.it.menu_api.presentation.MenuViewModel

class MenuViewModelImpl(
    private val menuRepository: MenuRepository
) : MenuViewModel() {

    override fun isDeviceConnected(): Flow<Boolean> = flow {
        menuRepository.isDeviceConnected()
    }
}