package com.phillqins.core.data.networking.di

import com.phillqins.core.data.networking.HttpClientFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreDataModule = module{
    single { HttpClientFactory().build() }
}