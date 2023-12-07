package com.example.satelliteprojectcompose.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.projectplayground.data.dto.SatelliteDetailDto
import com.example.projectplayground.data.dto.SatellitePosition
import com.example.satelliteprojectcompose.data.room.source.StringToIntConverter

@Database(
    entities = [SatelliteDetailDto::class, SatellitePosition::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SatellitePositionTypeConverters::class, StringToIntConverter::class) // Burada tip dönüştürücüyü belirtin
abstract class SatellitesDatabase : RoomDatabase() {
    abstract fun satelliteDao(): SatellitesDao
}