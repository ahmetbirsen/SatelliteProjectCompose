package com.example.satelliteprojectcompose.domain.use_case.get_satellite_detail

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.projectplayground.data.dto.toSatelliteDetail
import com.example.projectplayground.domain.model.SatelliteDetail
import com.example.satelliteprojectcompose.domain.repository.SatelliteRepository
import com.example.satelliteprojectcompose.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class GetSatelliteDetailsUseCase @Inject constructor(
    private val repository: SatelliteRepository
) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun executeGetSatelliteDetails(satelliteID: Int): Flow<Resource<SatelliteDetail?>> = flow {
        try {
            emit(Resource.Loading())
            val satelliteDetail = repository.getSatelliteDetail(satelliteID)
            if (satelliteDetail != null) {
                delay(500)
                val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val inputDate = LocalDate.parse(satelliteDetail.first_flight, inputFormatter)
                val outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                satelliteDetail.first_flight = outputFormatter.format(inputDate)
                emit(Resource.Success(satelliteDetail?.toSatelliteDetail()))
            } else {
                emit(Resource.Error("Not found any satellite detail !!"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: ""))
        }
    }
}