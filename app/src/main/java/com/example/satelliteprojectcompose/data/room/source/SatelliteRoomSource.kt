package com.example.satelliteprojectcompose.data.room.source

import com.example.projectplayground.data.dto.SatelliteDetailDto
import com.example.projectplayground.data.dto.SatellitePosition


interface SatelliteRoomSource{
    suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetailDto?
    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailDto)
    suspend fun deleteSatelliteDetails(satelliteDetail: SatelliteDetailDto)

    suspend fun getSatellitePositions(satelliteId: String) : SatellitePosition
    suspend fun insertSatellitePositions(satellitePosition: SatellitePosition)
    suspend fun deleteSatellitePositions(satellitePosition: SatellitePosition)

}