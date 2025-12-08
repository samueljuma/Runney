@file:OptIn(ExperimentalMaterial3Api::class)

package com.phillqins.run.presentation.active_run

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phillqins.core.presentation.designsystem.PauseIcon
import com.phillqins.core.presentation.designsystem.RunneyTheme
import com.phillqins.core.presentation.designsystem.StartIcon
import com.phillqins.core.presentation.designsystem.StopIcon
import com.phillqins.core.presentation.designsystem.components.RunneyDialog
import com.phillqins.core.presentation.designsystem.components.RunneyFloatingActionButton
import com.phillqins.core.presentation.designsystem.components.RunneyOutlinedActionButton
import com.phillqins.core.presentation.designsystem.components.RunneyScaffold
import com.phillqins.core.presentation.designsystem.components.RunneyToolbar
import com.phillqins.run.presentation.R
import com.phillqins.run.presentation.active_run.components.RunDataCard
import com.phillqins.run.presentation.util.hasLocationPermission
import com.phillqins.run.presentation.util.hasNotificationPermission
import com.phillqins.run.presentation.util.shouldShowLocationPermissionRationale
import com.phillqins.run.presentation.util.shouldShowNotificationPermissionRationale
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
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { perms ->
        val hasCoarseLocationPermission = perms[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        val hasFineLocationPermission = perms[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val hasNotificationPermission = if(Build.VERSION.SDK_INT >= 33){
            perms[Manifest.permission.POST_NOTIFICATIONS] == true
        }else true

        val activity = context as ComponentActivity
        val showLocationRationale = activity.shouldShowLocationPermissionRationale()
        val showNotificationRationale = activity.shouldShowNotificationPermissionRationale()

        onAction(
            ActiveRunAction.SubmitLocationPermissionInfo(
                acceptedLocationPermission = hasCoarseLocationPermission && hasFineLocationPermission,
                showLocationRationale = showLocationRationale
            )
        )
        onAction(
            ActiveRunAction.SubmitNotificationPermissionInfo(
                acceptedNotificationPermission = hasNotificationPermission,
                showNotificationRationale = showNotificationRationale,
            )
        )
    }

    LaunchedEffect(key1 = true) {
        val activity = context as ComponentActivity
        val showLocationRationale = activity.shouldShowLocationPermissionRationale()
        val showNotificationRationale = activity.shouldShowNotificationPermissionRationale()

        onAction(
            ActiveRunAction.SubmitLocationPermissionInfo(
                acceptedLocationPermission = context.hasLocationPermission(),
                showLocationRationale = showLocationRationale
            )
        )
        onAction(
            ActiveRunAction.SubmitNotificationPermissionInfo(
                acceptedNotificationPermission = context.hasNotificationPermission(),
                showNotificationRationale = showNotificationRationale,
            )
        )

        if(!showLocationRationale && !showNotificationRationale){
            permissionLauncher.requestRunneyPermissions(context)
        }
    }
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
    if(state.showLocationRationale || state.showNotificationRationale){
        RunneyDialog(
            title = stringResource(R.string.permission_required),
            description = when {
                state.showLocationRationale && state.showNotificationRationale -> {
                    stringResource(R.string.location_notification_rationale)
                }
                state.showLocationRationale -> stringResource(R.string.location_rationale)
                else -> stringResource(R.string.notification_rationale)
            },
            onDismiss = {/* Normal dismission not allowed for permissions */},
            primaryButton = {
                RunneyOutlinedActionButton(
                    text = stringResource(R.string.okay),
                    isLoading = false,
                    onClick = {
                        onAction(ActiveRunAction.DismissRationaleDialog)
                        permissionLauncher.requestRunneyPermissions(context)
                    },
                )
            },
        )
    }

}

private fun ActivityResultLauncher<Array<String>>.requestRunneyPermissions(
    context: Context
){
    val hasLocationPermission = context.hasLocationPermission()
    val hasNotificationPermission = context.hasNotificationPermission()

    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    val notificationPermissions = if(Build.VERSION.SDK_INT >= 33){
        arrayOf(Manifest.permission.POST_NOTIFICATIONS)
    } else arrayOf()

    when {
        !hasLocationPermission && hasNotificationPermission -> {
            launch(locationPermissions + notificationPermissions)
        }
        !hasLocationPermission -> launch(locationPermissions)
        !hasNotificationPermission -> launch(notificationPermissions)
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