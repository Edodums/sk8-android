package unibo.it.sk8

import android.app.Application
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import unibo.it.auth.di.authModule
import unibo.it.datastore.di.dataStoreModule
import unibo.it.domain.di.domainModule
import unibo.it.loading.di.loadingModule
import unibo.it.repository.di.repositoryModule
import unibo.it.sk8.di.appModule

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class SK8 : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@SK8)
            modules(
                appModule +
                    loadingModule +
                    authModule +
                    domainModule +
                    repositoryModule +
                    dataStoreModule
            )
        }
    }
}
