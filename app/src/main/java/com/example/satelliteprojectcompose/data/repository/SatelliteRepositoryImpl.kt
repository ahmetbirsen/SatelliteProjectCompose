package com.example.satelliteprojectcompose.data.repository


import com.example.projectplayground.data.dto.SatelliteDetailDto
import com.example.projectplayground.data.dto.SatelliteDtoItem
import com.example.projectplayground.data.dto.SatellitePosition
import com.example.projectplayground.data.file.source.SatelliteJsonSource
import com.example.satelliteprojectcompose.data.room.source.SatelliteRoomSource
import com.example.satelliteprojectcompose.domain.repository.SatelliteRepository
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val satellitesJsonSource : SatelliteJsonSource,
    private val satelliteDetailRoomSource: SatelliteRoomSource


) : SatelliteRepository {
    override suspend fun getSatellites() : List<SatelliteDtoItem> {
        return satellitesJsonSource.getSatellites()
    }

    override suspend fun getSearchSatellites(search: String): List<SatelliteDtoItem> {
        return satellitesJsonSource.getSearchSatellites(search)
    }
    override suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetailDto? {
        val cachedDetail = satelliteDetailRoomSource.getSatelliteDetail(satelliteId)
        return if (cachedDetail != null) {
            cachedDetail
        } else {
            val detailFromJson = satellitesJsonSource.getSatelliteDetail(satelliteId)
            detailFromJson?.let {
                satelliteDetailRoomSource.deleteSatelliteDetails(it)
                satelliteDetailRoomSource.insertSatelliteDetail(it)
            }
            detailFromJson
        }
    }

    override suspend fun getSatellitePositions(satelliteId: String): SatellitePosition? {
        val cachedPositions = satelliteDetailRoomSource.getSatellitePositions(satelliteId)
        return if (cachedPositions != null) {
            cachedPositions
        } else {
            val positionsFromJson = satelliteDetailRoomSource.getSatellitePositions(satelliteId)
            positionsFromJson?.let {
                satelliteDetailRoomSource.insertSatellitePositions(it)
            }
            positionsFromJson
        }
    }
}