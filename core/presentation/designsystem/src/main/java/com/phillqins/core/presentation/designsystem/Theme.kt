package com.phillqins.core.presentation.designsystem

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


val DarkColorScheme = darkColorScheme(
    primary = RunneyGreen,
    background = RunneyBlack,
    surface = RunneyDarkGray,
    secondary = RunneyWhite,
    tertiary = RunneyWhite,
    primaryContainer = RunneyGreen30,
    onPrimary = RunneyBlack,
    onBackground = RunneyWhite,
    onSurface = RunneyWhite,
    onSurfaceVariant = RunneyGray,
    onTertiary = RunneyBlack,
    error = RunneyDarkRed,
    errorContainer = RunneyDarkRed5
)

@Composable
fun RunneyTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if(!view.isInEditMode){
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )

}
