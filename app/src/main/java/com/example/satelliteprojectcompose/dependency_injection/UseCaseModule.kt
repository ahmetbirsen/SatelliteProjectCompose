package com.example.satelliteprojectcompose.dependency_injection

import com.example.projectplayground.domain.use_case.get_satellites.GetSatellitesUseCase
import com.example.projectplayground.domain.use_case.get_search_satellites.GetSearchSatellitesUseCase
import com.example.satelliteprojectcompose.domain.repository.SatelliteRepository
import com.example.satelliteprojectcompose.domain.use_case.SatelliteUseCases
import com.example.satelliteprojectcompose.domain.use_case.get_satellite_detail.GetSatelliteDetailsUseCase
import com.example.satelliteprojectcompose.domain.use_case.get_satellite_positions.GetSatellitePositionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
        @Provides
        @Singleton
        fun providesSatelliteUseCases(repository: SatelliteRepository): SatelliteUseCases {
            return SatelliteUseCases(
                getSatellite = GetSatellitesUseCase(repository),
                getSearchSatellite = GetSearchSatellitesUseCase(repository),
                getSatelliteDetail = GetSatelliteDetailsUseCase(repository),
                getSatellitePosition = GetSatellitePositionsUseCase(repository)

                )
        }
}