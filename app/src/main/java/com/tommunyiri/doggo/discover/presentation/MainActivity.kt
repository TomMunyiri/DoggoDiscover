package com.tommunyiri.doggo.discover.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tommunyiri.doggo.discover.presentation.screens.home.HomeScreen
import com.tommunyiri.doggo.discover.presentation.screens.home.HomeViewModel
import com.tommunyiri.doggo.discover.presentation.ui.theme.DoggoDiscoverTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DoggoDiscoverTheme {
                KoinAndroidContext {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        HomeScreen()
                    }
                }
            }
        }
    }
}

