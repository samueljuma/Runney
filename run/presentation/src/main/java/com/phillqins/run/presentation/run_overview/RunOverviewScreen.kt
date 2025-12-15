package com.phillqins.run.presentation.run_overview

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phillqins.core.presentation.designsystem.AnalyticsIcon
import com.phillqins.core.presentation.designsystem.LogoIcon
import com.phillqins.core.presentation.designsystem.RunIcon
import com.phillqins.core.presentation.designsystem.RunneyTheme
import com.phillqins.core.presentation.designsystem.components.RunneyFloatingActionButton
import com.phillqins.core.presentation.designsystem.components.RunneyScaffold
import com.phillqins.core.presentation.designsystem.components.RunneyToolbar
import com.phillqins.core.presentation.designsystem.components.util.DropDownItem
import com.phillqins.run.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun RunOverviewScreenRoot(
    onStartRunClick: () -> Unit,
    viewModel: RunOverviewViewModel  = koinViewModel()
) {
    RunOverviewScreen(
        onAction = { action ->
            when(action){
                is RunOverviewAction.OnStartClick -> onStartRunClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RunOverviewScreen(
    onAction: (RunOverviewAction) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )
    RunneyScaffold(
        topAppBar = {
            RunneyToolbar(
                showBackButton = false,
                title = stringResource(R.string.runney),
                scrollBehavior = scrollBehavior,
                menuItems = listOf(
                    DropDownItem(
                        icon = AnalyticsIcon,
                        title = stringResource(R.string.analytics)
                    ),
                    DropDownItem(
                        icon = AnalyticsIcon,
                        title = stringResource(R.string.logout)
                    ),
                ),
                onMenuItemClick = { index ->
                    when(index){
                        0 -> onAction(RunOverviewAction.OnAnalyticsClick)
                        1 -> onAction(RunOverviewAction.OnLogoutClick)
                    }

                },
                startContent = {
                    Icon(
                        imageVector = LogoIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            RunneyFloatingActionButton(
                icon = RunIcon,
                onClick = { onAction(RunOverviewAction.OnStartClick) }
            )
        }
    ) { padding ->

    }

}

@Preview
@Composable
private fun RunOverviewScreenPreview() {
    RunneyTheme{
       RunOverviewScreen(
           onAction = {}
       )
   }
}