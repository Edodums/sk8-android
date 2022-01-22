package unibo.it.datastore.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import unibo.it.datastore.datasource.UserPreferenceDataSourceImpl
import unibo.it.repository.datasource.UserPreferenceDataSource

val dataStoreModule = module {
    // Data source
    factory<UserPreferenceDataSource> {
        UserPreferenceDataSourceImpl(androidContext())
    }
}