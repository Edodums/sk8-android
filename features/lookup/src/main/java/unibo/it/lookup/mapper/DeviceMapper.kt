package unibo.it.lookup.mapper

import unibo.it.domain.model.Device as DomainDevice
import unibo.it.lookup_api.model.Device as ViewDevice

internal class DeviceMapper {
    fun toView(domainDevice: ViewDevice): ViewDevice =
        ViewDevice(
            UUID = domainDevice.UUID,
            address = domainDevice.address,
            name = domainDevice.name,
            data = domainDevice.data,
            isConnected = domainDevice.isConnected,
            createdAt = domainDevice.createdAt
        )

    fun toDomain(viewDevice: ViewDevice): DomainDevice =
        DomainDevice(
            UUID = viewDevice.UUID,
            address = viewDevice.address,
            name = viewDevice.name,
            data = viewDevice.data,
            isConnected = viewDevice.isConnected,
            createdAt = viewDevice.createdAt
        )
}