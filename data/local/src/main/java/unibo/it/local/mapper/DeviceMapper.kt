package unibo.it.local.mapper

import unibo.it.local.model.Device as LocalDevice
import unibo.it.repository.model.Device as RepoDevice

internal class DeviceMapper {
    fun fromRepo(repoDevice: RepoDevice): LocalDevice =
        LocalDevice(
            UUID = repoDevice.UUID,
            address = repoDevice.address,
            name = repoDevice.name,
            data = repoDevice.data,
            isConnected = repoDevice.isConnected,
            createdAt = repoDevice.createdAt
        )

    fun toRepo(localDevice: LocalDevice): RepoDevice =
        RepoDevice(
            UUID = localDevice.UUID,
            address = localDevice.address,
            name = localDevice.name,
            data = localDevice.data,
            isConnected = localDevice.isConnected,
            createdAt = localDevice.createdAt
        )
}