package com.tommunyiri.doggo.discover.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tommunyiri.doggo.discover.presentation.LocalMainContentPadding
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinViewModel()) {
    val contentPadding = LocalMainContentPadding.current
    val homeScreenState by homeViewModel.homeScreenState.collectAsStateWithLifecycle()
    if (homeScreenState.isLoading) {
        //LoadingIndicator()
        Log.d("TAG", "HomeScreen: isLoading")
    }

    homeScreenState.error?.let { error ->
        //FullScreenError(errorTitle = error, imageVector = AppIcons.Error)
        Log.d("TAG", "HomeScreen: Error: $error")
    }

    homeScreenState.dogsList?.let { dogList ->
        Log.d("TAG", "HomeScreen: Dog List Size: ${dogList.size}")
    }
}