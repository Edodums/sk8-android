package unibo.it.lookup.di

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import unibo.it.lookup.presentation.LookupViewModelImpl
import unibo.it.lookup_api.presentation.LookupViewModel

val lookupModule = module {
    viewModel<LookupViewModel> {
        LookupViewModelImpl(androidApplication())
    }
}