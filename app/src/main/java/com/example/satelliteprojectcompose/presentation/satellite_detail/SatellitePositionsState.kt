package com.example.satelliteprojectcompose.presentation.satellite_detail

import com.example.projectplayground.data.dto.SatellitePosition

data class SatellitePositionsState(
    val isLoading: Boolean = false,
    var satellitePositions: SatellitePosition? = null,
    val error: String = "",
    val currentIndex: Int = 0  // Bu satırı ekleyin

)