package com.phillqins.runney

import android.app.Application
import com.phillqins.auth.data.di.authDataModule
import com.phillqins.auth.presentation.di.authViewModelModule
import com.phillqins.core.data.di.coreDataModule
import com.phillqins.core.database.di.databaseModule
import com.phillqins.run.location.di.locationModule
import com.phillqins.run.presentation.di.runPresentationModule
import com.phillqins.runney.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RunneyApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger()
            androidContext(this@RunneyApp)
            modules(
                authDataModule,
                authViewModelModule,
                coreDataModule,
                appModule,
                runPresentationModule,
                locationModule,
                databaseModule
            )
        }
    }
}