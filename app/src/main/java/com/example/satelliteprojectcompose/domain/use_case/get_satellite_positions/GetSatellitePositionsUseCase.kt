package com.example.satelliteprojectcompose.domain.use_case.get_satellite_positions

import com.example.projectplayground.data.dto.SatellitePosition
import com.example.satelliteprojectcompose.domain.repository.SatelliteRepository
import com.example.satelliteprojectcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetSatellitePositionsUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) {

    fun executeGetSatellitePositions(satelliteId : String) : Flow<Resource<SatellitePosition>> = flow{
        try {
            emit(Resource.Loading())
            val satellitePositions = satelliteRepository.getSatellitePositions(satelliteId)
            emit(Resource.Success(satellitePositions))
        }catch (e : IOException){
            emit(Resource.Error(e.message ?: ""))
        }
    }
}