package unibo.it.local.di

import org.koin.dsl.module
import unibo.it.local.datasource.DeviceLocalDataSource
import unibo.it.local.mapper.DeviceMapper
import unibo.it.local.provider.DaoProvider
import unibo.it.local.provider.DatabaseProvider
import unibo.it.repository.datasource.DeviceDataSource

val localModule = module {
    // data sources
    single<DeviceDataSource> {DeviceLocalDataSource(get(), get())}
    // mappers
    factory { DeviceMapper() }
    // providers
    single { DatabaseProvider(get()) }
    single { DaoProvider(get()) }
}