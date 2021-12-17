package unibo.it.sk8.auth

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Singleton
@ExperimentalCoroutinesApi
class AuthRepository @Inject constructor(
    private val dataSource: AuthDataSource
)
