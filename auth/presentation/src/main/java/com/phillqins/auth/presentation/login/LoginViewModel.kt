package com.phillqins.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phillqins.auth.domain.AuthRepository
import com.phillqins.auth.domain.UserDataValidator
import com.phillqins.auth.presentation.R
import com.phillqins.core.domain.util.DataError
import com.phillqins.core.domain.util.Result
import com.phillqins.core.presentation.ui.UiText
import com.phillqins.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userDataValidator: UserDataValidator
): ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val eventChannel = Channel<LoginEvent>()
    val event = eventChannel.receiveAsFlow()

    init {
        combine(
            snapshotFlow { state.email.text },
            snapshotFlow { state.password.text }
        ) { email, password ->
            val isValidEmail = userDataValidator.isValidEmail(email.toString().trim())
            state = state.copy(
                canLogin = isValidEmail && password.isNotEmpty()
            )
        }.launchIn(viewModelScope)
    }


    fun onAction(action: LoginAction){
        when(action){
            is LoginAction.OnLoginClick -> login()
            is LoginAction.OnTogglePasswordVisibility -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }
            else -> Unit
        }
    }

    private fun login(){
        viewModelScope.launch {
            state = state.copy(isLoggingIn = true)
            val result = authRepository.login(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString()
            )
            state = state.copy(isLoggingIn = false)
            when(result){
                is Result.Error -> {
                    if(result.error == DataError.Network.UNAUTHORIZED){
                        eventChannel.send(LoginEvent.Error(
                            UiText.StringResource(R.string.error_email_password_incorrect)
                        ))
                    }else{
                        eventChannel.send(LoginEvent.Error(result.error.asUiText()))
                    }
                }
                is Result.Success -> eventChannel.send(LoginEvent.LoginSuccess)
            }

        }
    }
}