package com.phillqins.auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phillqins.auth.presentation.R
import com.phillqins.core.presentation.designsystem.EmailIcon
import com.phillqins.core.presentation.designsystem.Poppins
import com.phillqins.core.presentation.designsystem.RunneyTheme
import com.phillqins.core.presentation.designsystem.components.GradientBackground
import com.phillqins.core.presentation.designsystem.components.RunneyActionButton
import com.phillqins.core.presentation.designsystem.components.RunneyPasswordTextField
import com.phillqins.core.presentation.designsystem.components.RunneyTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel = koinViewModel()
) {
    LoginScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {
    GradientBackground{
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .padding(vertical = 16.dp, horizontal = 32.dp)
                .padding(top = 16.dp)
        ){
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.hi_there),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = stringResource(R.string.runney_welcome_text),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(48.dp))
            RunneyTextField(
                state = state.email,
                startIcon = EmailIcon,
                endIcon = null,
                hint = stringResource(R.string.example_email),
                title = stringResource(R.string.email),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            RunneyPasswordTextField(
                state = state.password,
                isPasswordVisible = state.isPasswordVisible,
                onTogglePasswordVisibility = { onAction(LoginAction.OnTogglePasswordVisibility) },
                hint = stringResource(R.string.password),
                title = stringResource(R.string.password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))
            RunneyActionButton(
                text = stringResource(R.string.login),
                isLoading = state.isLoggingIn,
                enabled = state.canLogin,
                onClick = { onAction(LoginAction.OnLoginClick) }
            )
            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = Poppins,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    append(stringResource(R.string.dont_have_an_account) + " ")
                    pushLink(
                        LinkAnnotation.Clickable(
                            tag = "register",
                            linkInteractionListener = {
                                onAction(LoginAction.OnRegisterClick)
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
                        append(stringResource(R.string.sign_up))
                    }
                    pop()
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 32.dp),
                contentAlignment = Alignment.BottomCenter
            ){
                Text(
                    text = annotatedString,
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    RunneyTheme{
       LoginScreen(
           state = LoginState(),
           onAction = {}
       )
   }
}