package com.tommunyiri.doggo.discover.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tommunyiri.doggo.discover.core.INITIAL_LIST_LIMIT
import com.tommunyiri.doggo.discover.core.INITIAL_LIST_PAGE
import com.tommunyiri.doggo.discover.core.handleException
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
        val (title, message) = exception.handleException()
        _homeScreenState.update {
            it.copy(
                isLoading = false,
                isLoadingMore = false,
                error = Error(title, message)
            )
        }
    }

    fun getDogs(loadMore: Boolean = false) {
        if (loadMore && (!homeScreenState.value.canLoadMore || homeScreenState.value.isLoadingMore)) {
            return
        }

        val isFirstLoad = !loadMore
        if (isFirstLoad) {
            _homeScreenState.update { it.copy(isLoading = true) }
        } else {
            _homeScreenState.update { it.copy(isLoadingMore = true) }
        }

        viewModelScope.launch(exceptionHandler) {
            val page =
                if (isFirstLoad) INITIAL_LIST_PAGE else homeScreenState.value.currentPage + 1
            val limit = INITIAL_LIST_LIMIT

            getDogsUseCase.invoke(page = page, limit = limit).asResult().collect { result ->
                when (result) {
                    is Result.Success -> {
                        val newDogs = result.data
                        _homeScreenState.update { currentState ->
                            currentState.copy(
                                dogsList = if (isFirstLoad) newDogs else currentState.dogsList + newDogs,
                                isLoading = false,
                                isLoadingMore = false,
                                error = null,
                                currentPage = page,
                                canLoadMore = newDogs.size >= limit
                            )
                        }
                    }

                    is Result.Error -> {
                        val (title, message) = result.error.handleException()
                        _homeScreenState.update {
                            it.copy(
                                isLoading = false,
                                isLoadingMore = false,
                                error = Error(title, message),
                            )
                        }
                    }
                }
            }
        }
    }

    fun loadMoreDogs() {
        getDogs(loadMore = true)
    }
}

data class HomeScreenUIState(
    val dogsList: List<DogInfo> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val canLoadMore: Boolean = true,
    val currentPage: Int = INITIAL_LIST_PAGE,
    val error: Error? = null
)

data class Error(val errorTitle: Int, val errorMessage: Any)