package unibo.it.repository.di

import org.koin.dsl.module
import unibo.it.domain.repository.AuthRepository
import unibo.it.repository.AuthRepositoryImpl


val repositoryModule = module {
    // Repositories
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}