package com.example.satelliteprojectcompose.data.room

import androidx.room.*
import com.example.projectplayground.data.dto.SatelliteDetailDto
import com.example.projectplayground.data.dto.SatellitePosition

@Dao
interface SatellitesDao {

    //For satellite detail
    @Query("SELECT * FROM SATELLITE_DETAIL WHERE id = :id")
    suspend fun getSatelliteDetail(id: Int): SatelliteDetailDto?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailDto)
    @Delete
    suspend fun deleteSatelliteDetails(satelliteDetail: SatelliteDetailDto)


    //For satellite positions
    @Query("SELECT * FROM SATELLITE_POSITION WHERE id = :satelliteId LIMIT 1")
    suspend fun getSatellitePositions(satelliteId: String): SatellitePosition?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSatellitePositions(satellitePositions: SatellitePosition)
    @Delete
    suspend fun deleteSatellitePositions(satellitePositions: SatellitePosition)
}