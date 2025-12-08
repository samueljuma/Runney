package com.phillqins.run.presentation.di

import com.phillqins.run.presentation.active_run.ActiveRunViewModel
import com.phillqins.run.presentation.run_overview.RunOverviewViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val runViewModelModule = module {
    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}