package unibo.it.sk8

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import unibo.it.auth.di.authModule
import unibo.it.core.di.coreModule
import unibo.it.datastore.di.dataStoreModule
import unibo.it.domain.di.domainModule
import unibo.it.repository.di.repositoryModule
import unibo.it.sk8.di.appModule

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
                    coreModule +
                    authModule +
                    domainModule +
                    repositoryModule +
                    dataStoreModule
            )
        }
    }
}
