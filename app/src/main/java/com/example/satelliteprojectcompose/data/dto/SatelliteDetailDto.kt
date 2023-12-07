package com.example.projectplayground.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projectplayground.domain.model.SatelliteDetail
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "SATELLITE_DETAIL")
data class SatelliteDetailDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val cost_per_launch: Long,
    var first_flight: String,
    val height: Int,
    val mass: Long
)

fun SatelliteDetailDto.toSatelliteDetail() : SatelliteDetail{
    return SatelliteDetail(
        id,
        cost_per_launch,
        first_flight,
        height,
        mass
    )
}

