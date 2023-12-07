package com.example.satelliteprojectcompose.domain.use_case


import com.example.projectplayground.domain.use_case.get_satellites.GetSatellitesUseCase
import com.example.projectplayground.domain.use_case.get_search_satellites.GetSearchSatellitesUseCase

data class SatelliteUseCases(
    val getSatellite: GetSatellitesUseCase,
    val getSearchSatellite : GetSearchSatellitesUseCase,

    )