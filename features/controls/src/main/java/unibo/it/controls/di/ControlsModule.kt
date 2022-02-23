package unibo.it.controls.di

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import unibo.it.controls.presentation.ControlsViewModelImpl
import unibo.it.controls_api.presentation.ControlsViewModel

val controlsModule = module {
    viewModel<ControlsViewModel>() {
        ControlsViewModelImpl(androidApplication(), get())
    }
}