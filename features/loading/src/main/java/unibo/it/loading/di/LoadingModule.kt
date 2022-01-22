package unibo.it.loading.di

import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import unibo.it.loading.presentation.LoadingViewModelImpl
import unibo.it.loading_api.presentation.LoadingViewModel


@InternalCoroutinesApi
val loadingModule = module {
    viewModel<LoadingViewModel> {
        LoadingViewModelImpl(get())
    }
}