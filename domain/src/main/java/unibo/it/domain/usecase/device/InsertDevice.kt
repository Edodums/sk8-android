package unibo.it.domain.usecase.device

import unibo.it.domain.model.Device

interface InsertDevice {
    suspend operator fun invoke(device: Device)
}