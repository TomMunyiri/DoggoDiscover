package com.tommunyiri.doggo.discover.presentation.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tommunyiri.doggo.discover.R
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
fun FavoritesScreen(onDogClick: (DogInfo) -> Unit) {
    val favoritesViewModel: FavoritesViewModel = koinViewModel()
    val favoriteScreenState by favoritesViewModel.favoriteScreenState.collectAsStateWithLifecycle()
    val contentPadding = LocalMainContentPadding.current
    val context = LocalContext.current

    if (favoriteScreenState.isLoading) {
        LoadingIndicator()
        return
    }

    favoriteScreenState.error?.let { error ->
        val message = error.errorMessage.anyToString(context = context)
        val title = stringResource(error.errorTitle)
        FullScreenError(errorTitle = title, errorDesc = message, imageVector = AppIcons.Error)
        return
    }

    if (favoriteScreenState.dogsList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            FullScreenError(
                errorTitle = stringResource(R.string.no_favorites_title),
                errorDesc = stringResource(R.string.no_favorites_desc),
                imageVector = AppIcons.FavoriteOutlined,
            )
        }
        return
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(GRID_ITEM_COUNT),
        modifier = Modifier.fillMaxSize(),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        items(favoriteScreenState.dogsList) { dogInfo ->
            DogHomeCard(
                dogInfo = dogInfo,
                onDogClick = onDogClick,
            )
        }
    }
}