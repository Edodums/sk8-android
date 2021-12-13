package unibo.it.sk8.auth

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: AuthRepository
): ViewModel(), AuthenticateView {


    override fun authenticate() {
        TODO("Not yet implemented")
    }

    override fun signUp() {
        TODO("Not yet implemented")
    }

    override fun signIn() {
        TODO("Not yet implemented")
    }

    override fun toggleAuthenticationMode() {
        TODO("Not yet implemented")
    }

    override fun setEmailAddress(emailAddress: String) {
        TODO("Not yet implemented")
    }

    override fun setPassword(password: String) {
        TODO("Not yet implemented")
    }

    // check petrangola for the cache
}
