package unibo.it.repository

import kotlinx.coroutines.flow.Flow
import unibo.it.domain.repository.MenuRepository
import unibo.it.repository.datasource.DeviceDataSource

internal class MenuRepositoryImpl(
    private val deviceDataSource: DeviceDataSource
) : MenuRepository {
    override fun isDeviceConnected(): Flow<Boolean> =
        deviceDataSource.isLastDeviceConnected()

}