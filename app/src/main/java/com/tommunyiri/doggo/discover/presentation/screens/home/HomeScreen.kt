package com.tommunyiri.doggo.discover.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tommunyiri.doggo.discover.core.GRID_ITEM_COUNT
import com.tommunyiri.doggo.discover.core.anyToString
import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.presentation.LocalMainContentPadding
import com.tommunyiri.doggo.discover.presentation.components.AppIcons
import com.tommunyiri.doggo.discover.presentation.components.DogHomeCard
import com.tommunyiri.doggo.discover.presentation.components.FullScreenError
import com.tommunyiri.doggo.discover.presentation.components.LoadingIndicator
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(onDogClick: (DogInfo) -> Unit = {}) {
    val homeViewModel: HomeViewModel = koinViewModel()
    val contentPadding = LocalMainContentPadding.current
    val homeScreenState by homeViewModel.homeScreenState.collectAsStateWithLifecycle()
    val gridState = rememberLazyGridState()

    // Calculate if user is close to the bottom of the list
    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisibleItem = gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleItem >= gridState.layoutInfo.totalItemsCount - GRID_ITEM_COUNT
        }
    }

    val context = LocalContext.current

    // Load more items when user is close to the bottom
    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore && !homeScreenState.isLoading && !homeScreenState.isLoadingMore && homeScreenState.canLoadMore) {
            homeViewModel.loadMoreDogs()
        }
    }

    if (homeScreenState.isLoading) {
        LoadingIndicator()
        return
    }

    homeScreenState.error?.let { error ->
        val message = error.errorMessage.anyToString(context = context)
        val title = stringResource(error.errorTitle)
        FullScreenError(errorTitle = title, errorDesc = message, imageVector = AppIcons.Error)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(GRID_ITEM_COUNT),
        state = gridState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        items(homeScreenState.dogsList) { dogInfo ->
            DogHomeCard(
                dogInfo = dogInfo,
                onDogClick = onDogClick,
            )
        }

        if (homeScreenState.isLoadingMore) {
            item(span = { GridItemSpan(GRID_ITEM_COUNT) }) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    LoadingIndicator()
                }
            }
        }
    }
}