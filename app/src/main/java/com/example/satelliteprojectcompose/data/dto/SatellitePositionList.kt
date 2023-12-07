package com.example.projectplayground.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SatellitePositionList(
    val list: List<SatellitePosition>
)