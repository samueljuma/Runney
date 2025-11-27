package com.phillqins.runney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.phillqins.auth.presentation.intro.IntroScreen
import com.phillqins.core.presentation.designsystem.RunneyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RunneyTheme {
                IntroScreen(
                    onAction = {}
                )
            }
        }
    }
}