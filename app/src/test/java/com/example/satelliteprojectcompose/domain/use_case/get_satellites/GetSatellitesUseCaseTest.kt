package com.example.satelliteprojectcompose.domain.use_case.get_satellites

import com.example.projectplayground.data.dto.toSatellite
import com.example.projectplayground.domain.use_case.get_satellites.GetSatellitesUseCase
import com.example.satelliteprojectcompose.data.repository.FakeSatelliteRepository
import com.example.satelliteprojectcompose.util.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class GetSatellitesUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var fakeSatelliteRepository: FakeSatelliteRepository
    private lateinit var getSatellitesUseCase: GetSatellitesUseCase

    @Before
    fun setup() {
        fakeSatelliteRepository = FakeSatelliteRepository()
        getSatellitesUseCase = GetSatellitesUseCase(fakeSatelliteRepository)
    }

    @Test
    fun `executeGetSatellites should return Loading and Success states`() = testDispatcher.runBlockingTest {
        val expectedResult = fakeSatelliteRepository.getSatellites().map { it.toSatellite() }

        val flow = getSatellitesUseCase.executeGetSatellites()
        var loadingCount = 0
        var successCount = 0

        flow.collect { result ->
            when (result) {
                is Resource.Loading -> {
                    loadingCount++
                }
                is Resource.Success -> {
                    assertThat(result.data).isEqualTo(expectedResult)
                    successCount++
                }
                is Resource.Error -> {
                    throw AssertionError("Expected loading or success, but was error: ${result.message}")
                }
            }
        }
        assertThat(loadingCount).isGreaterThan(0)
        assertThat(successCount).isEqualTo(1)
    }


    @Test
    fun `executeGetSatellites should return Error state`() = testDispatcher.runBlockingTest {
        val errorMessage = "An error occurred"
        val flow = getSatellitesUseCase.executeGetSatellites()
        var errorCount = 0

        flow.collect { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    throw AssertionError("Expected error, but was success")
                }
                is Resource.Error -> {
                    assertThat(result.message).isEqualTo(errorMessage)
                    errorCount++
                }
            }
        }
        assertThat(errorCount).isEqualTo(1)
    }

    @Test
    fun `executeGetSatellites should return Success state with empty list`() = testDispatcher.runBlockingTest {
        fakeSatelliteRepository.setSatellites(emptyList())
        val flow = getSatellitesUseCase.executeGetSatellites()
        var successCount = 0

        flow.collect { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    assertThat(result.data).isEmpty()
                    successCount++
                }
                is Resource.Error -> {
                    throw AssertionError("Expected success, but was error: ${result.message}")
                }
            }
        }

        assertThat(successCount).isEqualTo(1)
    }



}


