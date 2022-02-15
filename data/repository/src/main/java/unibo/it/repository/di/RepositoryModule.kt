package unibo.it.repository.di

import org.koin.dsl.module
import unibo.it.domain.repository.AuthRepository
import unibo.it.domain.repository.LookupRepository
import unibo.it.domain.repository.MenuRepository
import unibo.it.domain.repository.SettingRepository
import unibo.it.repository.AuthRepositoryImpl
import unibo.it.repository.LookupRepositoryImpl
import unibo.it.repository.MenuRepositoryImpl
import unibo.it.repository.SettingRepositoryImpl
import unibo.it.repository.mapper.DeviceMapper
import unibo.it.repository.mapper.SettingMapper
import unibo.it.repository.mapper.UserDataMapper


val repositoryModule = module {
    // Repositories
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<MenuRepository> { MenuRepositoryImpl(get(), get()) }
    single<LookupRepository> { LookupRepositoryImpl(get(), get()) }
    single<SettingRepository> { SettingRepositoryImpl(get(), get()) }
    // Mappers
    factory { UserDataMapper() }
    factory { DeviceMapper() }
    factory { SettingMapper() }
}