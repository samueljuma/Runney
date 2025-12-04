package com.phillqins.core.data.di

import com.phillqins.core.data.auth.EncryptedSessionStorage
import com.phillqins.core.data.networking.HttpClientFactory
import com.phillqins.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module{
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
    single { HttpClientFactory(get ()).build() }
}