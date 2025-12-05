package com.phillqins.run.presentation.run_overview

import androidx.lifecycle.ViewModel

class RunOverviewViewModel(): ViewModel(){

    fun onAction(action: RunOverviewAction){
        when(action){
            is RunOverviewAction.OnAnalyticsClick -> TODO()
            is RunOverviewAction.OnLogoutClick -> TODO()
            is RunOverviewAction.OnStartClick -> TODO()
        }
    }
}
