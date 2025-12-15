package com.phillqins.run.presentation.run_overview

import androidx.lifecycle.ViewModel

class RunOverviewViewModel(): ViewModel(){

    fun onAction(action: RunOverviewAction){
        when(action){
            is RunOverviewAction.OnAnalyticsClick -> {}
            is RunOverviewAction.OnLogoutClick -> {}
            is RunOverviewAction.OnStartClick -> {}
        }
    }
}
