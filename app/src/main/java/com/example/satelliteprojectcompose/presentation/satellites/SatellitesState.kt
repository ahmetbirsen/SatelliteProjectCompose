package com.example.satelliteprojectcompose.presentation.satellites

import com.example.projectplayground.data.dto.SatelliteDtoItem
import com.example.projectplayground.domain.model.Satellite

data class SatellitesState(
    val isLoading: Boolean = false,
    val satellites: List<Satellite> = emptyList() ,
    val error: String = ""
)