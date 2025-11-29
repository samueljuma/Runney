package com.phillqins.auth.data.di

import com.phillqins.auth.data.EmailPatternValidator
import com.phillqins.auth.domain.PatternValidator
import com.phillqins.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> { EmailPatternValidator }
    singleOf(::UserDataValidator)
}