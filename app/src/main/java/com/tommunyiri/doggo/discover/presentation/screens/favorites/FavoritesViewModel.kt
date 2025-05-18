package com.tommunyiri.doggo.discover.presentation.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tommunyiri.doggo.discover.core.handleException
import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.domain.usecases.GetFavoritesUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
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

    private fun getFavoriteDogs() {
        viewModelScope.launch(exceptionHandler) {
            _favoriteScreenState.update { it.copy(isLoading = true) }
            getFavoritesUseCase.invoke().collect { favorites ->
                _favoriteScreenState.update {
                    it.copy(
                        dogsList = favorites,
                        isLoading = false,
                        error = null,
                    )
                }
            }
        }
    }
}

data class FavoritesScreenUIState(
    val dogsList: List<DogInfo> = emptyList(),
    val isLoading: Boolean = false,
    val error: Error? = null,
)

data class Error(val errorTitle: Int, val errorMessage: Any)