package unibo.it.repository

import unibo.it.domain.model.Device
import unibo.it.domain.repository.LookupRepository
import unibo.it.repository.datasource.DeviceDataSource
import unibo.it.repository.mapper.DeviceMapper

internal class LookupRepositoryImpl constructor(
    private val deviceDataSource: DeviceDataSource,
    private val deviceMapper: DeviceMapper
) : LookupRepository {
    override suspend fun insertDevice(device: Device) {
        deviceDataSource.insertDevice(deviceMapper.toRepo(device))
    }
}