package unibo.it.auth.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import unibo.it.auth.presentation.AuthViewModelImpl
import unibo.it.auth.presentation.textfield.otp.OTPViewModel
import unibo.it.auth_api.presentation.AuthViewModel

@ExperimentalCoroutinesApi
val authModule = module {
    viewModel<AuthViewModel>() {
        AuthViewModelImpl(get())
    }

    viewModel { OTPViewModel() }
}