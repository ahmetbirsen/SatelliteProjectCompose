package com.example.satelliteprojectcompose.presentation.satellite_detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectplayground.data.file.source.SatelliteJsonSource
import com.example.satelliteprojectcompose.data.room.source.SatelliteRoomSource
import com.example.satelliteprojectcompose.domain.use_case.SatelliteUseCases
import com.example.satelliteprojectcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private val stateHandleForSendingValueFromRoute: SavedStateHandle,
    private val satelliteUseCases: SatelliteUseCases,
    private val satelliteJsonSource: SatelliteJsonSource,
    private val satelliteRoomSource: SatelliteRoomSource
) : ViewModel() {

    private val _state = mutableStateOf<SatelliteDetailState>(SatelliteDetailState(positions = emptyList()))
    val state : State<SatelliteDetailState> = _state

    private val _positionsState = mutableStateOf<SatellitePositionsState>(SatellitePositionsState())
    val positionsState: State<SatellitePositionsState> = _positionsState

    init {
        stateHandleForSendingValueFromRoute.get<Int>("satelliteId")?.let {
            viewModelScope.launch {
                val json = satelliteJsonSource.getSatellitePositions(satelliteId = it.toString())
                satelliteRoomSource.insertSatellitePositions(json)
                getSatellitePosition(it)
            }
            getSatelliteDetail(it)

        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun getSatelliteDetail(satelliteID : Int){
        satelliteUseCases.getSatelliteDetail.executeGetSatelliteDetails(satelliteID = satelliteID).onEach {
            when(it){
                is Resource.Loading -> {
                    _state.value = SatelliteDetailState(isLoading = true,positions = emptyList())
                }

                is Resource.Success -> {
                    _state.value = SatelliteDetailState(satellite = it.data, positions = emptyList())
                }

                is Resource.Error -> {
                    _state.value = SatelliteDetailState(error = it.message ?: "Error!", positions = emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getSatellitePosition(satelliteID : Int){
        satelliteUseCases.getSatellitePosition.executeGetSatellitePositions(satelliteId = satelliteID.toString()).onEach {
            when(it){
                is Resource.Loading -> {
                    _positionsState.value = SatellitePositionsState(isLoading = true)
                }

                is Resource.Success -> {
                    _positionsState.value = SatellitePositionsState(satellitePositions = it.data)
                }

                is Resource.Error -> {

                    _positionsState.value = SatellitePositionsState(error = it.message ?: "Error!")
                }
            }
        }.launchIn(viewModelScope)
    }



}