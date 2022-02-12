package unibo.it.local.datasource

import unibo.it.local.mapper.DeviceMapper
import unibo.it.local.provider.DaoProvider
import unibo.it.repository.datasource.DeviceDataSource
import unibo.it.repository.model.Device

internal class DeviceLocalDataSource(
    daoProvider: DaoProvider,
    private val deviceMapper: DeviceMapper,
) : DeviceDataSource {
    private val deviceDao = daoProvider.getDeviceDao()

    override suspend fun insertDevice(device: Device) =
        deviceDao.insertDevice(deviceMapper.fromRepo(device))

    override suspend fun updateDevice(device: Device) =
        deviceDao.updateDevice(deviceMapper.fromRepo(device))

    override suspend fun deleteDevice(device: Device) =
        deviceDao.deleteDevice(deviceMapper.fromRepo(device))

    override suspend fun purge() =
        deviceDao.purge()

    override suspend fun findDeviceByPrimaryKeys(
        deviceAddress: String,
        deviceName: String,
    ): Device? =
        deviceDao.findDeviceByPrimaryKeys(deviceAddress, deviceName)?.let {
            deviceMapper.toRepo(it)
        }

    override suspend fun getLastDevice(): Device? =
        deviceDao.getLastDevice()?.let { device ->
            deviceMapper.toRepo(device)
        }
}