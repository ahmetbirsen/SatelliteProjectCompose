package com.example.satelliteprojectcompose.domain.use_case


import com.example.projectplayground.domain.use_case.get_satellites.GetSatellitesUseCase
import com.example.projectplayground.domain.use_case.get_search_satellites.GetSearchSatellitesUseCase
import com.example.satelliteprojectcompose.domain.use_case.get_satellite_detail.GetSatelliteDetailsUseCase
import com.example.satelliteprojectcompose.domain.use_case.get_satellite_positions.GetSatellitePositionsUseCase

data class SatelliteUseCases(
    val getSatellite: GetSatellitesUseCase,
    val getSearchSatellite : GetSearchSatellitesUseCase,
    val getSatelliteDetail : GetSatelliteDetailsUseCase,
    val getSatellitePosition : GetSatellitePositionsUseCase

    )