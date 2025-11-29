package com.phillqins.runney

import android.app.Application
import com.phillqins.auth.data.di.authDataModule
import com.phillqins.auth.presentation.di.authViewModelModule
import com.phillqins.core.data.networking.di.coreDataModule
import com.phillqins.runney.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RunneyApp: Application() {
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
                appModule
            )
        }
    }
}