package com.example.projectplayground.domain.use_case.get_search_satellites

import com.example.projectplayground.data.dto.toSatellite
import com.example.projectplayground.domain.model.Satellite
import com.example.satelliteprojectcompose.domain.repository.SatelliteRepository
import com.example.satelliteprojectcompose.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetSearchSatellitesUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) {
    fun executeGetSearchSatellites (search : String) : Flow<Resource<List<Satellite>>> = flow{
        try {
            emit(Resource.Loading())
            val satelliteList = satelliteRepository.getSearchSatellites(search).map { satellite ->
                satellite.toSatellite()
            }
            if (satelliteList != null){
                delay(500)
                emit(Resource.Success(satelliteList))
            }
            else if(satelliteList == null){
                emit(Resource.Error("Not found!"))
            }
            else{
                emit(Resource.Error("Error"))
            }
        }catch (e : IOException){
            emit(Resource.Error(e.message ?: ""))
        }
    }
}