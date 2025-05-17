package com.tommunyiri.doggo.discover.presentation.viewmodels

import com.tommunyiri.doggo.discover.core.INITIAL_LIST_LIMIT
import com.tommunyiri.doggo.discover.core.INITIAL_LIST_PAGE
import com.tommunyiri.doggo.discover.domain.usecases.GetDogsUseCase
import com.tommunyiri.doggo.discover.fakeDogInfo
import com.tommunyiri.doggo.discover.presentation.screens.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private val getDogsUseCase = mockk<GetDogsUseCase>(relaxed = true)
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        homeViewModel = HomeViewModel(getDogsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getDogs correctly updates ui state dogs list after getting dogs from get dogs use case`() =
        runTest {
            val dogs = listOf(fakeDogInfo)

            // Given
            coEvery {
                getDogsUseCase.invoke(
                    page = INITIAL_LIST_PAGE,
                    limit = INITIAL_LIST_LIMIT
                )
            } returns flowOf(dogs)

            // When
            homeViewModel.getDogs()
            coVerify {
                getDogsUseCase.invoke(
                    page = INITIAL_LIST_PAGE,
                    limit = INITIAL_LIST_LIMIT
                )
            }

            // Then
            val uiState = homeViewModel.homeScreenState.value
            assertEquals(dogs, uiState.dogsList)
        }
}