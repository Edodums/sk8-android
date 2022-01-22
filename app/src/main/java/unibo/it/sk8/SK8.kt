package unibo.it.sk8

import android.app.Application
<<<<<<< HEAD
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import unibo.it.auth.di.authModule
import unibo.it.controls.di.controlsModule
import unibo.it.datastore.di.dataStoreModule
import unibo.it.domain.di.domainModule
import unibo.it.loading.di.loadingModule
import unibo.it.lookup.di.lookupModule
import unibo.it.menu.di.menuModule
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
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@SK8)
            modules(
                appModule +
                    loadingModule +
                    authModule +
                    menuModule +
                    lookupModule +
                    controlsModule +
                    domainModule +
                    repositoryModule +
                    dataStoreModule
            )
        }
    }
}
=======
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SK8 : Application()
>>>>>>> main
