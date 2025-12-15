package com.phillqins.run.presentation.di

import com.phillqins.run.domain.RunningTracker
import com.phillqins.run.presentation.active_run.ActiveRunViewModel
import com.phillqins.run.presentation.run_overview.RunOverviewViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val runPresentationModule = module {
    singleOf(::RunningTracker)
    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}