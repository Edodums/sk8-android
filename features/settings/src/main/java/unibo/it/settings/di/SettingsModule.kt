package unibo.it.settings.di

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import unibo.it.settings.mapper.SettingMapper
import unibo.it.settings.presentation.SettingsViewModelImpl
import unibo.it.settings_api.presentation.SettingsViewModel

val settingsModule = module {
    viewModel<SettingsViewModel>() {
        SettingsViewModelImpl(androidApplication(), get(), get(), get())
    }

    factory { SettingMapper() }
}