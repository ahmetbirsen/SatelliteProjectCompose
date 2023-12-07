package com.example.projectplayground.data.file.source

import android.content.Context
import com.example.projectplayground.data.dto.SatelliteDetailDto
import com.example.projectplayground.data.dto.SatelliteDtoItem
import com.example.projectplayground.data.dto.SatellitePosition
import com.example.projectplayground.data.dto.SatellitePositionList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream


@OptIn(ExperimentalSerializationApi::class)
class SatellitesJsonSourceImpl(
    private val context: Context
) : SatelliteJsonSource {
    override suspend fun getSatellites(): List<SatelliteDtoItem> = withContext(Dispatchers.IO) {
        try {
            context.assets.open("satellite_list.json").use { inputStream ->
                val satellitesList = Json.decodeFromStream<List<SatelliteDtoItem>>(inputStream)
                satellitesList
            }
        } catch (e: Exception) {
            println("Error while getting satellites: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getSearchSatellites(search: String): List<SatelliteDtoItem> = withContext(Dispatchers.IO) {
        try {
            val allSatellites = getSatellites()
            val filteredSatellites = allSatellites.filter { it.name.contains(search, ignoreCase = true) }
            filteredSatellites
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getSatelliteDetail(id: Int): SatelliteDetailDto? = withContext(Dispatchers.IO) {
        try {
            val satelliteDetailList: List<SatelliteDetailDto> =
                context.assets.open("satellite_detail.json").use { inputStream ->
                    Json.decodeFromStream(inputStream)
                }
            satelliteDetailList.find { it.id == id }
        } catch (e: Exception) {
            println("Error while getting satellite detail: ${e.message}")
            null
        }
    }

    override suspend fun getSatellitePositions(satelliteId: String): SatellitePosition = withContext(Dispatchers.IO) {
        try {
            val positionsList: SatellitePositionList = context.assets.open("positions.json")
                .use { inputStream ->
                    Json.decodeFromStream(inputStream)
                }
            val satellitePosition = positionsList.list.find {
                it.id.toString() == satelliteId
            }
            satellitePosition ?: SatellitePosition(id = 0, positions = emptyList())
        } catch (e: Exception) {
            println("Error while getting satellite positions: ${e.message}")
            SatellitePosition(id = 0, positions = emptyList())
        }
    }

}