package com.phillqins.auth.presentation.di

import com.phillqins.auth.presentation.login.LoginViewModel
import com.phillqins.auth.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf

import org.koin.dsl.module

val  authViewModelModule  = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}