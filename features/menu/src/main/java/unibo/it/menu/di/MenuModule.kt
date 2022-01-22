package unibo.it.menu.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import unibo.it.menu.presentation.MenuViewModelImpl
import unibo.it.menu_api.presentation.MenuViewModel

val menuModule = module {
    viewModel<MenuViewModel>() {
        MenuViewModelImpl(get())
    }
}