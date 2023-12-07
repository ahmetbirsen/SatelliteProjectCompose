package com.example.satelliteprojectcompose.domain.repository


import com.example.projectplayground.data.dto.SatelliteDetailDto
import com.example.projectplayground.data.dto.SatelliteDtoItem
import com.example.projectplayground.data.dto.SatellitePosition

interface SatelliteRepository {
    suspend fun getSatellites(): List<SatelliteDtoItem>
    suspend fun getSearchSatellites(search : String) : List<SatelliteDtoItem>

    suspend fun getSatelliteDetail(satelliteId : Int) : SatelliteDetailDto?
    suspend fun getSatellitePositions(satelliteId : String) : SatellitePosition?
}