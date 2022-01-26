package unibo.it.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import unibo.it.domain.model.UserData
import unibo.it.domain.repository.AuthRepository
import unibo.it.repository.datasource.UserPreferenceDataSource
import unibo.it.repository.mapper.UserDataMapper


internal class AuthRepositoryImpl constructor(
    private val userPreferenceDataSource: UserPreferenceDataSource,
    private val userDataMapper: UserDataMapper
) : AuthRepository {
    override suspend fun saveToken(userData: UserData) =
        userPreferenceDataSource.saveToken(userDataMapper.toRepo(userData))

    override suspend fun loadData(): Flow<UserData> =
        userPreferenceDataSource.loadData().map {
            userDataMapper.toDomain(it)
        }
}
