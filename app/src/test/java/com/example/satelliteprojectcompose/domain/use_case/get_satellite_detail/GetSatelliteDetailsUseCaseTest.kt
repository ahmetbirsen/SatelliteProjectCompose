package com.example.satelliteprojectcompose.domain.use_case.get_satellite_detail

import com.example.projectplayground.data.dto.SatelliteDetailDto
import com.example.satelliteprojectcompose.data.repository.FakeSatelliteRepository
import com.example.satelliteprojectcompose.util.Resource
import com.google.common.truth.Truth
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetSatelliteDetailsUseCaseTest {
    private val testDispatcher = TestCoroutineDispatcher()


    private lateinit var fakeSatelliteRepository: FakeSatelliteRepository
    private lateinit var getSatelliteDetailsUseCase: GetSatelliteDetailsUseCase

    @Before
    fun setup() {
        fakeSatelliteRepository = FakeSatelliteRepository()
        getSatelliteDetailsUseCase = GetSatelliteDetailsUseCase(fakeSatelliteRepository)
    }

    @Test
    fun `executeGetSatelliteDetails emits loading state and then success state with details`() =
        testDispatcher.runBlockingTest {
            val satelliteId = 1
            val satelliteDetail = SatelliteDetailDto(
                id = satelliteId,
                cost_per_launch = 1000000,
                first_flight = "2022-01-01",
                height = 100,
                mass = 5000
            )

            fakeSatelliteRepository.setSatelliteDetail(satelliteDetail)

            var loadingCount = 0
            var successCount = 0
            getSatelliteDetailsUseCase.executeGetSatelliteDetails(satelliteId).collect {
                when (it) {
                    is Resource.Loading -> loadingCount++
                    is Resource.Success -> successCount++
                    else -> Assert.fail("Unexpected result type")
                }
            }

            Truth.assertThat(loadingCount).isEqualTo(1)
            Truth.assertThat(successCount).isEqualTo(1)
        }

    @Test
    fun `executeGetSatelliteDetails emits loading state and then error state when details retrieval fails`() =
        testDispatcher.runBlockingTest {
            val satelliteId = 1

            var loadingCount = 0
            var errorCount = 0
            getSatelliteDetailsUseCase.executeGetSatelliteDetails(satelliteId).collect {
                when (it) {
                    is Resource.Loading -> loadingCount++
                    is Resource.Error -> errorCount++
                    else -> {}
                }
            }

            Truth.assertThat(loadingCount).isEqualTo(1)
            Truth.assertThat(errorCount).isEqualTo(1)
        }
}