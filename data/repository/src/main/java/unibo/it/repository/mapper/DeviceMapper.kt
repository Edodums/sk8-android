package unibo.it.repository.mapper

import unibo.it.domain.model.Device as DomainDevice
import unibo.it.repository.model.Device as RepoDevice

internal class DeviceMapper {
    fun toRepo(domainDevice: DomainDevice): RepoDevice =
        RepoDevice(
            UUID = domainDevice.UUID,
            address = domainDevice.address,
            name = domainDevice.name,
            data = domainDevice.data,
            createdAt = domainDevice.createdAt,
            isConnected = domainDevice.isConnected
        )

    fun toDomain(repoDevice: RepoDevice): DomainDevice =
        DomainDevice(
            UUID = repoDevice.UUID,
            address = repoDevice.address,
            name = repoDevice.name,
            data = repoDevice.data,
            createdAt = repoDevice.createdAt,
            isConnected = repoDevice.isConnected
        )
}