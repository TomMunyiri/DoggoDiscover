package com.tommunyiri.doggo.discover.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tommunyiri.doggo.discover.presentation.LocalMainContentPadding
import com.tommunyiri.doggo.discover.presentation.components.DogHomeCard
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = contentPadding,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(dogList) { dogInfo ->
                DogHomeCard(dogInfo = dogInfo)
            }
        }
    }
}