package com.phillqins.run.data.di

import com.phillqins.core.domain.run.SyncRunScheduler
import com.phillqins.run.data.CreateRunWorker
import com.phillqins.run.data.DeleteRunWorker
import com.phillqins.run.data.FetchRunsWorker
import com.phillqins.run.data.SyncRunWorkerScheduler
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::FetchRunsWorker)
    workerOf(::CreateRunWorker)
    workerOf(::DeleteRunWorker)
    singleOf(::SyncRunWorkerScheduler).bind<SyncRunScheduler>()
}