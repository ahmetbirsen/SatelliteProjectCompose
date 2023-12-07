package com.example.projectplayground.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = "SATELLITE_POSITION")
data class SatellitePosition(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val positions: List<Position>
)
