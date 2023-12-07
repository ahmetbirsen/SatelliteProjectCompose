package com.example.satelliteprojectcompose.data.room

import androidx.room.TypeConverter
import com.example.projectplayground.data.dto.Position
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SatellitePositionTypeConverters {
    @TypeConverter
    fun fromJson(json: String): List<Position> {
        return try {
            Json.decodeFromString<List<Position>>(json)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun toJson(positions: List<Position>): String {
        return Json.encodeToString(positions)
    }
}