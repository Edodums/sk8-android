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
            createdAt = domainDevice.createdAt,
            isConnected = domainDevice.isConnected
        )

    fun toDomain(viewDevice: ViewDevice): DomainDevice =
        DomainDevice(
            UUID = viewDevice.UUID,
            address = viewDevice.address,
            name = viewDevice.name,
            data = viewDevice.data,
            createdAt = viewDevice.createdAt,
            isConnected = viewDevice.isConnected
        )
}