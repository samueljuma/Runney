@file:OptIn(ExperimentalMaterial3Api::class)

package com.phillqins.run.presentation.active_run

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phillqins.core.presentation.designsystem.PauseIcon
import com.phillqins.core.presentation.designsystem.RunneyTheme
import com.phillqins.core.presentation.designsystem.StartIcon
import com.phillqins.core.presentation.designsystem.StopIcon
import com.phillqins.core.presentation.designsystem.components.RunneyFloatingActionButton
import com.phillqins.core.presentation.designsystem.components.RunneyScaffold
import com.phillqins.core.presentation.designsystem.components.RunneyToolbar
import com.phillqins.presentation.ui.R
import com.phillqins.run.presentation.active_run.components.RunDataCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun ActiveRunScreenRoot(
    viewModel: ActiveRunViewModel = koinViewModel()
) {
    ActiveRunScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun ActiveRunScreen(
    state: ActiveRunState,
    onAction: (ActiveRunAction) -> Unit
) {
    RunneyScaffold(
        withGradient = false,
        topAppBar = {
            RunneyToolbar(
                showBackButton = true,
                title = stringResource(R.string.active_run),
                onBackClick = { onAction(ActiveRunAction.OnBackClick) }
            )
        },
        floatingActionButton = {
            RunneyFloatingActionButton(
                icon = if (state.shouldTrack) StopIcon else StartIcon,
                onClick = { onAction(ActiveRunAction.OnToggleRunClick) },
                iconSize = 20.dp,
                contentDescription = if (state.shouldTrack) {
                    stringResource(R.string.pause_run)
                } else {
                    stringResource(R.string.start_run)
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ){
            RunDataCard(
                elapsedTime = state.elapsedTime,
                runData = state.runData,
                modifier = Modifier
                    .padding(16.dp)
                    .padding(padding)
                    .fillMaxWidth()
            )
        }

    }

}

@Preview
@Composable
private fun ActiveRunScreenPreview() {
    RunneyTheme{
       ActiveRunScreen(
           state = ActiveRunState(),
           onAction = {}
       )
   }
}