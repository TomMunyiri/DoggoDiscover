package com.tommunyiri.doggo.discover.presentation.screens.dogdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tommunyiri.doggo.discover.core.handleException
import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.domain.usecases.AddFavoriteUseCase
import com.tommunyiri.doggo.discover.domain.usecases.IsFavoriteUseCase
import com.tommunyiri.doggo.discover.domain.usecases.RemoveFavoriteUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DogDetailsViewModel(
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : ViewModel() {
    private val _dogDetailsScreenState = MutableStateFlow(DogDetailsScreenUIState())
    val dogDetailsScreenState: StateFlow<DogDetailsScreenUIState> =
        _dogDetailsScreenState.asStateFlow()
    private val exceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            val (title, message) = exception.handleException()
            _dogDetailsScreenState.update {
                it.copy(
                    error = Error(title, message),
                )
            }
        }

    fun isFavorite(id: Int) {
        viewModelScope.launch(exceptionHandler) {
            isFavoriteUseCase.invoke(id).collect { isFavorite ->
                _dogDetailsScreenState.update { it.copy(isFavorite = isFavorite) }
            }
        }
    }

    fun addFavorite(dogInfo: DogInfo) {
        viewModelScope.launch {
            addFavoriteUseCase.invoke(dogInfo)
        }
    }

    fun removeFavorite(id: Int) {
        viewModelScope.launch {
            removeFavoriteUseCase.invoke(id)
        }
    }
}


data class DogDetailsScreenUIState(
    val error: Error? = null,
    val isFavorite: Boolean? = null
)

data class Error(val errorTitle: Int, val errorMessage: Any)