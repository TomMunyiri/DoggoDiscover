package com.tommunyiri.doggo.discover.data.repositories

import com.tommunyiri.doggo.discover.data.sources.remote.RemoteDogsDataSource
import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.domain.model.NetworkDogInfo
import com.tommunyiri.doggo.discover.fakeDogInfo
import com.tommunyiri.doggo.discover.fakeDogList
import com.tommunyiri.doggo.discover.fakeNetworkDogInfo
import com.tommunyiri.doggo.discover.fakeNetworkDogList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DogRepositoryImplTest {
    private val remoteDataSource = mockk<RemoteDogsDataSource>()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: DogRepositoryImpl

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = DogRepositoryImpl(remoteDataSource, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getDogs returns mapped domain models from remote source`() = runTest {
        // Given
        val page = 0
        val limit = 20
        val remoteDogs = fakeNetworkDogList
        val expectedDogs = fakeDogList

        coEvery { remoteDataSource.getDogs(page, limit) } returns flowOf(remoteDogs)

        // When
        val result = repository.getDogs(page, limit).first()

        // Then
        assertEquals(expectedDogs, result)
    }

    @Test
    fun `getDogs returns empty list when remote source returns empty`() = runTest {
        // Given
        val page = 0
        val limit = 20
        val remoteDogs = emptyList<NetworkDogInfo>()
        val expectedDogs = emptyList<DogInfo>()

        coEvery { remoteDataSource.getDogs(page, limit) } returns flowOf(remoteDogs)

        // When
        val result = repository.getDogs(page, limit).first()

        // Then
        assertEquals(expectedDogs, result)
    }

    @Test
    fun `getDogs passes correct pagination parameters to remote source`() = runTest {
        // Given
        val page = 2
        val limit = 15
        val remoteDogs = emptyList<NetworkDogInfo>()

        coEvery { remoteDataSource.getDogs(page, limit) } returns flowOf(remoteDogs)

        // When
        repository.getDogs(page, limit).first()

        // Then
        coVerify(exactly = 1) { remoteDataSource.getDogs(page, limit) }
    }

    @Test
    fun `getDogs maps all fields correctly from DTO to domain model`() = runTest {
        // Given
        val page = 0
        val limit = 1
        val remoteDto = fakeNetworkDogInfo
        val expectedDog = fakeDogInfo

        coEvery { remoteDataSource.getDogs(page, limit) } returns flowOf(listOf(remoteDto))

        // When
        val result = repository.getDogs(page, limit).first()

        // Then
        assertEquals(listOf(expectedDog), result)
    }
}
