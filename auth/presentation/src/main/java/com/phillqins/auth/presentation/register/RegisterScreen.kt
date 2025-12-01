package com.phillqins.auth.presentation.register

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phillqins.auth.domain.PasswordValidationState
import com.phillqins.auth.domain.UserDataValidator
import com.phillqins.auth.presentation.R
import com.phillqins.core.presentation.designsystem.CheckIcon
import com.phillqins.core.presentation.designsystem.CrossIcon
import com.phillqins.core.presentation.designsystem.EmailIcon
import com.phillqins.core.presentation.designsystem.Poppins
import com.phillqins.core.presentation.designsystem.RunneyDarkRed
import com.phillqins.core.presentation.designsystem.RunneyGray
import com.phillqins.core.presentation.designsystem.RunneyGreen
import com.phillqins.core.presentation.designsystem.RunneyTheme
import com.phillqins.core.presentation.designsystem.components.GradientBackground
import com.phillqins.core.presentation.designsystem.components.RunneyActionButton
import com.phillqins.core.presentation.designsystem.components.RunneyPasswordTextField
import com.phillqins.core.presentation.designsystem.components.RunneyTextField
import com.phillqins.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel


@Composable
fun RegisterScreenRoot(
    onSignInClick: () -> Unit,
    onSuccessfulRegistration: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    ObserveAsEvents(flow = viewModel.events) { event ->
        when(event){
            is RegisterEvent.Error ->{
                keyboardController?.hide()
                Toast.makeText(context, event.error.asString(context), Toast.LENGTH_SHORT).show()
            }
            RegisterEvent.RegistrationSuccess -> {
                keyboardController?.hide()
                Toast.makeText(context, R.string.registration_successful, Toast.LENGTH_SHORT).show()
                onSuccessfulRegistration()
            }
        }
    }
    RegisterScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    GradientBackground{
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 32.dp)
                .padding(top = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.create_account),
                style = MaterialTheme.typography.headlineMedium
            )

            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = Poppins,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    append(stringResource(R.string.already_have_an_account) + " ")
                    pushLink(
                        LinkAnnotation.Clickable(
                            tag = "login",
                            linkInteractionListener = {
                                onAction(RegisterAction.OnLoginClick)
                            }
                        )
                    )
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = Poppins
                        )
                    ) {
                        append(stringResource(R.string.login))
                    }
                    pop()
                }
            }
            Text(
                text = annotatedString
            )
            Spacer(modifier = Modifier.height(48.dp))
            RunneyTextField(
                state = state.email,
                startIcon = EmailIcon,
                endIcon = if(state.isEmailValid) CheckIcon else null,
                hint = stringResource(R.string.example_email),
                title = stringResource(R.string.email),
                modifier = Modifier.fillMaxWidth(),
                additionalInfo = stringResource(R.string.must_be_a_valid_email),
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(16.dp))
            RunneyPasswordTextField(
                state = state.password,
                isPasswordVisible = state.isPasswordVisible,
                onTogglePasswordVisibility = {onAction(RegisterAction.OnTogglePasswordVisibilityClick)},
                hint = stringResource(R.string.password),
                title = stringResource(R.string.password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordRequirement(
                text = stringResource(R.string.at_least_x_characters, UserDataValidator.MIN_PASSWORD_LENGTH),
                isValid = state.passwordValidationState.hasMinLength
            )
            Spacer(modifier = Modifier.height(4.dp))
            PasswordRequirement(
                text = stringResource(R.string.at_least_one_number),
                isValid = state.passwordValidationState.hasNumber
            )
            Spacer(modifier = Modifier.height(4.dp))
            PasswordRequirement(
                text = stringResource(R.string.contains_lowercase_char),
                isValid = state.passwordValidationState.hasLowerCaseCharacter
            )
            Spacer(modifier = Modifier.height(4.dp))
            PasswordRequirement(
                text = stringResource(R.string.contains_uppercase_char),
                isValid = state.passwordValidationState.hasUpperCaseCharacter
            )
            Spacer(modifier = Modifier.height(32.dp))
            RunneyActionButton(
                text = stringResource(R.string.register),
                isLoading = state.isRegistering,
                enabled = state.canRegister,
                modifier = Modifier.fillMaxWidth(),
                onClick = { onAction(RegisterAction.OnRegisterClick) }
            )
        }
    }
}

@Composable
fun PasswordRequirement(
    text: String,
    isValid: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = if(isValid) CheckIcon else CrossIcon,
            contentDescription = null,
            tint = if(isValid) RunneyGreen else RunneyDarkRed
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 15.sp
        )
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    RunneyTheme{
        RegisterScreen(
            state = RegisterState(
                passwordValidationState = PasswordValidationState(
                    hasNumber = true,
                    hasUpperCaseCharacter = true,
                )
            ),
            onAction = {}
        )
    }
}