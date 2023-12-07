package com.example.satelliteprojectcompose.data.room.source

import com.example.projectplayground.data.dto.SatelliteDetailDto
import com.example.projectplayground.data.dto.SatellitePosition
import com.example.satelliteprojectcompose.data.room.SatellitesDao


class SatelliteRoomSourceImpl (
    private val satellitesDao: SatellitesDao
) : SatelliteRoomSource {
    override suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetailDto? {
        return satellitesDao.getSatelliteDetail(satelliteId)
    }

    override suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailDto) {
        satellitesDao.insertSatelliteDetail(satelliteDetail)
    }

    override suspend fun deleteSatelliteDetails(satelliteDetail: SatelliteDetailDto) {
        satellitesDao.deleteSatelliteDetails(satelliteDetail)
    }

    override suspend fun getSatellitePositions(satelliteId: String): SatellitePosition? {
        return satellitesDao.getSatellitePositions(satelliteId)
    }


    override suspend fun insertSatellitePositions(satellitePosition: SatellitePosition) {
        satellitesDao.deleteSatellitePositions(satellitePosition)
        satellitesDao.insertSatellitePositions(satellitePosition)
    }

    override suspend fun deleteSatellitePositions(satellitePosition: SatellitePosition) {
        satellitesDao.deleteSatellitePositions(satellitePosition)
    }

}