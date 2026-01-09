package com.phillqins.core.data.di

import com.phillqins.core.data.auth.EncryptedSessionStorage
import com.phillqins.core.data.networking.HttpClientFactory
import com.phillqins.core.data.run.OfflineFirstRunRepository
import com.phillqins.core.domain.SessionStorage
import com.phillqins.core.domain.run.RunRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module{
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
    single { HttpClientFactory(get ()).build() }
    singleOf(::OfflineFirstRunRepository).bind<RunRepository>()
}