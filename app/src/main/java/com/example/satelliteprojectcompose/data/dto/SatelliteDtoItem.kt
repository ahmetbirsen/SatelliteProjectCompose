package com.example.projectplayground.data.dto

import com.example.projectplayground.domain.model.Satellite
import kotlinx.serialization.Serializable

@Serializable
data class SatelliteDtoItem(
    val active: Boolean,
    val id: Int,
    val name: String
)

fun SatelliteDtoItem.toSatellite(): Satellite {
    return Satellite(
        active,
        id,
        name
    )
}
