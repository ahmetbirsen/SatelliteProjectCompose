package com.example.satelliteprojectcompose.presentation.satellites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.satelliteprojectcompose.domain.use_case.SatelliteUseCases
import com.example.satelliteprojectcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SatellitesViewModel @Inject constructor(
    private val satelliteUseCases: SatelliteUseCases
): ViewModel() {

    private val _state = mutableStateOf<SatellitesState>(SatellitesState())
    val state : State<SatellitesState> = _state

    private var job : Job? = null

    init {
        getSatellites()
    }

    private fun getSatellites() {
        satelliteUseCases.getSatellite.executeGetSatellites().onEach {
            when(it){
                is Resource.Loading -> {
                    _state.value = SatellitesState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = SatellitesState(satellites = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = SatellitesState(error = it.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getSearchSatellites(search : String){
        job?.cancel()
        job = satelliteUseCases.getSearchSatellite.executeGetSearchSatellites(search).onEach {
            delay(500)
            when(it){
                is Resource.Loading -> {
                    _state.value = SatellitesState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = SatellitesState(satellites = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = SatellitesState(error = it.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)

    }
}