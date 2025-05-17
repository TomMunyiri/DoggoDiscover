package com.tommunyiri.doggo.discover.domain.usecases

import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.domain.repositories.DogRepository
import com.tommunyiri.doggo.discover.fakeDogList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetDogsUseCaseTest {
    private val dogRepository = mockk<DogRepository>()
    private lateinit var getDogsUseCase: GetDogsUseCase

    @Before
    fun setup() {
        getDogsUseCase = GetDogsUseCase(dogRepository)
    }

    @Test
    fun `invoke returns dogs list from repository`() = runTest {
        // Given
        val page = 0
        val limit = 20
        val expectedDogs = fakeDogList

        coEvery { dogRepository.getDogs(page, limit) } returns flowOf(expectedDogs)

        // When
        val result = getDogsUseCase.invoke(page, limit).first()

        // Then
        assertEquals(expectedDogs, result)
    }

    @Test
    fun `invoke returns empty list when repository returns empty`() = runTest {
        // Given
        val page = 0
        val limit = 20
        val expectedDogs = emptyList<DogInfo>()

        coEvery { dogRepository.getDogs(page, limit) } returns flowOf(expectedDogs)

        // When
        val result = getDogsUseCase.invoke(page, limit).first()

        // Then
        assertEquals(expectedDogs, result)
    }

    @Test
    fun `invoke passes correct pagination parameters to repository`() = runTest {
        // Given
        val page = 2
        val limit = 15
        val expectedDogs = emptyList<DogInfo>()

        coEvery { dogRepository.getDogs(page, limit) } returns flowOf(expectedDogs)

        // When
        getDogsUseCase.invoke(page, limit).first()

        // Then
        coVerify(exactly = 1) { dogRepository.getDogs(page, limit) }
    }
}
