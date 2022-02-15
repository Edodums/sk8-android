package unibo.it.local.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import unibo.it.local.datasource.DeviceLocalDataSource
import unibo.it.local.datasource.SettingLocalDataSource
import unibo.it.local.mapper.DeviceMapper
import unibo.it.local.mapper.SettingMapper
import unibo.it.local.provider.DaoProvider
import unibo.it.local.provider.DatabaseProvider
import unibo.it.repository.datasource.DeviceDataSource
import unibo.it.repository.datasource.SettingDataSource

val localModule = module {
    // data sources
    single<DeviceDataSource> { DeviceLocalDataSource(get(), get()) }
    single<SettingDataSource> { SettingLocalDataSource(get(), get()) }
    // mappers
    factory { DeviceMapper() }
    factory { SettingMapper() }
    // providers
    single { DatabaseProvider(get(), get()) }
    single { DaoProvider(get()) }

    factory { CoroutineScope(Dispatchers.IO) } // you need to specify this or else the coroutineScope won't be defined and it'll launch an
}