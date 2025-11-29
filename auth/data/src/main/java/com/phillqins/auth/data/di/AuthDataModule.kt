package com.phillqins.auth.data.di

import com.phillqins.auth.data.AuthRepositoryImpl
import com.phillqins.auth.data.EmailPatternValidator
import com.phillqins.auth.domain.AuthRepository
import com.phillqins.auth.domain.PatternValidator
import com.phillqins.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> { EmailPatternValidator }
    singleOf(::UserDataValidator)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}