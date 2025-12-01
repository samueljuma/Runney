package com.phillqins.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.phillqins.auth.domain.AuthRepository

class LoginViewModel(
    private val authRepository: AuthRepository
): ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onAction(action: LoginAction){

    }
}