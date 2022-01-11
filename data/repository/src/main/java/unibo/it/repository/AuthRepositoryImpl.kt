package unibo.it.repository

import unibo.it.domain.repository.AuthRepository
import unibo.it.repository.datasource.UserPreferenceDataSource

class AuthRepositoryImpl constructor(
    private val userPreferenceDataSource: UserPreferenceDataSource
): AuthRepository {
    override suspend fun saveToken(token: String, email: String) =
        userPreferenceDataSource.saveToken(token, email)
}
