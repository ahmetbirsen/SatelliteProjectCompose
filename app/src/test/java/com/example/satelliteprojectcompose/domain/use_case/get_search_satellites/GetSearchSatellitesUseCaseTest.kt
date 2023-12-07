package com.example.satelliteprojectcompose.domain.use_case.get_search_satellites

import com.example.projectplayground.data.dto.SatelliteDtoItem
import com.example.projectplayground.domain.use_case.get_search_satellites.GetSearchSatellitesUseCase
import com.example.satelliteprojectcompose.data.repository.FakeSatelliteRepository
import com.example.satelliteprojectcompose.util.Resource
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class GetSearchSatellitesUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var fakeSatelliteRepository: FakeSatelliteRepository
    private lateinit var getSearchSatellitesUseCase: GetSearchSatellitesUseCase

    @Before
    fun setup() {
        fakeSatelliteRepository = FakeSatelliteRepository()
        getSearchSatellitesUseCase = GetSearchSatellitesUseCase(fakeSatelliteRepository)
    }

    @Test
    fun `executeGetSearchSatellites emits loading state and then success state with search results`() =
        testDispatcher.runBlockingTest {
            // Arrange
            val searchQuery = "Falcon"
            val searchResult = listOf(
                SatelliteDtoItem(active = true, id = 1, name = "Starlink"),
                SatelliteDtoItem(active = true, id = 2, name = "Turksat")
            )
            fakeSatelliteRepository.setSearchSatellites(searchResult)

            var loadingCount = 0
            var successCount = 0
            getSearchSatellitesUseCase.executeGetSearchSatellites(searchQuery).collect {
                when (it) {
                    is Resource.Loading -> loadingCount++
                    is Resource.Success -> successCount++
                    else -> fail("Unexpected result type")
                }
            }
            Truth.assertThat(loadingCount).isGreaterThan(0)
            Truth.assertThat(successCount).isEqualTo(1)
        }


    @Test
    fun `executeGetSearchSatellites emits loading state and then empty success state when search results are empty`() =
        testDispatcher.runBlockingTest {
            val searchQuery = "EmptySearch"
            fakeSatelliteRepository.setSearchSatellites(emptyList())

            var loadingCount = 0
            var successCount = 0
            getSearchSatellitesUseCase.executeGetSearchSatellites(searchQuery).collect {
                when (it) {
                    is Resource.Loading -> loadingCount++
                    is Resource.Success -> successCount++
                    else -> fail("Unexpected result type")
                }
            }
            Truth.assertThat(loadingCount).isGreaterThan(0)
            Truth.assertThat(successCount).isEqualTo(1)
        }
}