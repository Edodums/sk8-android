package unibo.it.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import unibo.it.domain.repository.MenuRepository
import unibo.it.repository.datasource.UserPreferenceDataSource

internal class MenuRepositoryImpl(
    private val userPreferenceDataSource: UserPreferenceDataSource
) : MenuRepository {
    override suspend fun isDeviceConnected(): Flow<Boolean> = flow {
        userPreferenceDataSource.loadData().map {
            it?.deviceConnected
        }
    }
}