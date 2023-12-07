package com.example.projectplayground.domain.use_case.get_satellites

import com.example.projectplayground.data.dto.toSatellite
import com.example.projectplayground.domain.model.Satellite
import com.example.satelliteprojectcompose.domain.repository.SatelliteRepository
import com.example.satelliteprojectcompose.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetSatellitesUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) {
    fun executeGetSatellites () : Flow<Resource<List<Satellite>>> = flow {
        try {
            emit(Resource.Loading())
            val satelliteList = satelliteRepository.getSatellites().map { satellite ->
                satellite.toSatellite()
            }
            if (satelliteList != null){
                delay(1000)
                emit(Resource.Success(satelliteList))
            }
        }catch (e : IOException) {
            emit(Resource.Error(e.message ?: ""))
        }
    }

}