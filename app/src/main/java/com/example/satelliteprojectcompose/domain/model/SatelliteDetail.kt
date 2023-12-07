package com.example.projectplayground.domain.model

data class SatelliteDetail(
    val id: Int,
    val cost_per_launch: Long,
    val first_flight: String,
    val height: Int,
    val mass: Long
)
