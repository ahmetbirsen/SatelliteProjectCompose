package com.example.satelliteprojectcompose.dependency_injection

import com.example.projectplayground.data.file.source.SatelliteJsonSource
import com.example.satelliteprojectcompose.data.repository.SatelliteRepositoryImpl
import com.example.satelliteprojectcompose.data.room.source.SatelliteRoomSource
import com.example.satelliteprojectcompose.domain.repository.SatelliteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestRepositoryModule {

    @Singleton
    @Provides
    fun provideSatelliteRepository(
        satelliteJsonSource: SatelliteJsonSource,
        satelliteDetailRoomSource : SatelliteRoomSource
    ): SatelliteRepository {
        return SatelliteRepositoryImpl(satelliteJsonSource,satelliteDetailRoomSource)
    }


}