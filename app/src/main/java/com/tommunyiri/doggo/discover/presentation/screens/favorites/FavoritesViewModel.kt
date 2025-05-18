package com.tommunyiri.doggo.discover.presentation.screens.favorites

import androidx.lifecycle.ViewModel
import com.tommunyiri.doggo.discover.core.handleException
import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.domain.usecases.AddFavoriteUseCase
import com.tommunyiri.doggo.discover.domain.usecases.IsFavoriteUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FavoritesViewModel(
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase
) : ViewModel() {
    private val _favoriteScreenState = MutableStateFlow(FavoritesScreenUIState())
    val favoriteScreenState: StateFlow<FavoritesScreenUIState> = _favoriteScreenState.asStateFlow()
    private val exceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            val (title, message) = exception.handleException()
            _favoriteScreenState.update {
                it.copy(
                    isLoading = false,
                    error = Error(title, message),
                )
            }
        }

    init {
        getFavoriteDogs()
    }

    fun getFavoriteDogs() {

    }
}

data class FavoritesScreenUIState(
    val dogsList: List<DogInfo> = emptyList(),
    val isLoading: Boolean = false,
    val error: Error? = null,
    val isFavorite: Boolean = false
)

data class Error(val errorTitle: Int, val errorMessage: Any)