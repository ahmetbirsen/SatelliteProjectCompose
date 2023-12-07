package com.example.satelliteprojectcompose.presentation.satellite_detail

import com.example.projectplayground.data.dto.SatellitePosition
import com.example.projectplayground.domain.model.SatelliteDetail

data class SatelliteDetailState(
    val isLoading : Boolean = false,
    val satellite : SatelliteDetail? = null,
    val positions : SatellitePosition? = null,
    val error : String = ""
)