package com.example.satelliteprojectcompose.data.repository


import com.example.projectplayground.data.dto.SatelliteDtoItem
import com.example.projectplayground.data.file.source.SatelliteJsonSource
import com.example.satelliteprojectcompose.domain.repository.SatelliteRepository
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val satellitesJsonSource : SatelliteJsonSource

) : SatelliteRepository {
    override suspend fun getSatellites() : List<SatelliteDtoItem> {
        return satellitesJsonSource.getSatellites()
    }

    override suspend fun getSearchSatellites(search: String): List<SatelliteDtoItem> {
        return satellitesJsonSource.getSearchSatellites(search)
    }

}