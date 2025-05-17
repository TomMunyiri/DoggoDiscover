package com.tommunyiri.doggo.discover.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tommunyiri.doggo.discover.core.utils.Result
import com.tommunyiri.doggo.discover.core.utils.asResult
import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.domain.usecases.GetDogsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val getDogsUseCase: GetDogsUseCase) : ViewModel() {
    private val _homeScreenState = MutableStateFlow(HomeScreenUIState())
    val homeScreenState: StateFlow<HomeScreenUIState> = _homeScreenState.asStateFlow()
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _homeScreenState.update { it.copy(isLoading = false, error = exception.localizedMessage) }
    }

    init {
        getDogs()
    }

    private fun getDogs() {
        _homeScreenState.update { it.copy(isLoading = true) }
        viewModelScope.launch(exceptionHandler) {
            getDogsUseCase.invoke().asResult().collect { result ->
                when (result) {
                    is Result.Success -> _homeScreenState.update {
                        it.copy(dogsList = result.data, isLoading = false, error = null)
                    }

                    is Result.Error -> _homeScreenState.update {
                        it.copy(
                            dogsList = null, isLoading = false, error = result.error,
                        )
                    }
                }
            }
        }
    }
}

data class HomeScreenUIState(
    val dogsList: List<DogInfo>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)