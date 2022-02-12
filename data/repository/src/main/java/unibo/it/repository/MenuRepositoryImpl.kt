package unibo.it.repository

import unibo.it.domain.model.Device
import unibo.it.domain.repository.MenuRepository
import unibo.it.repository.datasource.DeviceDataSource
import unibo.it.repository.mapper.DeviceMapper

internal class MenuRepositoryImpl(
    private val deviceDataSource: DeviceDataSource,
    private val deviceMapper: DeviceMapper,
) : MenuRepository {
    override suspend fun getLastDevice(): Device? =
        deviceDataSource.getLastDevice()?.let {
            deviceMapper.toDomain(it)
        }

    override suspend fun updateDevice(device: Device) {
        deviceDataSource.updateDevice(deviceMapper.toRepo(device))
    }
}