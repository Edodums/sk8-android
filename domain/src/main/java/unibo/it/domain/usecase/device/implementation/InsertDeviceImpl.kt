package unibo.it.domain.usecase.device.implementation

import unibo.it.domain.model.Device
import unibo.it.domain.repository.LookupRepository
import unibo.it.domain.usecase.device.InsertDevice

internal class InsertDeviceImpl(
    private val lookupRepository: LookupRepository
): InsertDevice {
    override suspend operator fun invoke(device: Device) {
        lookupRepository.insertDevice(device)
    }
}