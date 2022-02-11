package unibo.it.domain.di

import org.koin.dsl.module
import unibo.it.domain.usecase.device.InsertDevice
import unibo.it.domain.usecase.device.implementation.InsertDeviceImpl

val domainModule = module {
    // Device use cases
    factory<InsertDevice> { InsertDeviceImpl(get()) }
}