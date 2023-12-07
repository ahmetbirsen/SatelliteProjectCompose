package com.example.satelliteprojectcompose.data.repository

import com.example.projectplayground.data.dto.Position
import com.example.projectplayground.data.dto.SatelliteDetailDto
import com.example.projectplayground.data.dto.SatelliteDtoItem
import com.example.projectplayground.data.dto.SatellitePosition
import com.example.satelliteprojectcompose.domain.repository.SatelliteRepository

class FakeSatelliteRepository : SatelliteRepository {

    fun setSatellites(satellites: List<SatelliteDtoItem>) {
        this.satellites = satellites
    }

    fun setSearchSatellites(satellites: List<SatelliteDtoItem>) {
        searchSatellites = satellites
    }

    fun setSatelliteDetail(satelliteDetail: SatelliteDetailDto?) {
        this.satelliteDetail = satelliteDetail
    }


    private var satellites: List<SatelliteDtoItem> = listOf(
        SatelliteDtoItem(true, 1, "Satellite 1"),
        SatelliteDtoItem(true, 2, "Satellite 2")
    )


    private var searchSatellites: List<SatelliteDtoItem> = emptyList()


    private var satelliteDetail: SatelliteDetailDto? = SatelliteDetailDto(
        id = 1,
        cost_per_launch = 1000000,
        first_flight = "2020-10-03",
        height = 100,
        mass = 2000
    )

    private val satellitePositions: SatellitePosition? = SatellitePosition(
        id = 1,
        positions = listOf(
            Position( 40.7128, -74.0060),
            Position( 34.0522, -118.2437)
        )
    )

    override suspend fun getSatellites(): List<SatelliteDtoItem> {
        return satellites
    }

    override suspend fun getSearchSatellites(search: String): List<SatelliteDtoItem> {
        return satellites.filter { it.name.contains(search, ignoreCase = true) }
    }
}