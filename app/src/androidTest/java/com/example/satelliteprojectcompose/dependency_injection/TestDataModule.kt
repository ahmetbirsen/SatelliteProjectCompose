package com.example.satelliteprojectcompose.dependency_injection

import android.content.Context
import androidx.room.Room
import com.example.projectplayground.data.file.source.SatelliteJsonSource
import com.example.projectplayground.data.file.source.SatellitesJsonSourceImpl
import com.example.satelliteprojectcompose.data.room.SatellitesDao
import com.example.satelliteprojectcompose.data.room.SatellitesDatabase
import com.example.satelliteprojectcompose.data.room.source.SatelliteRoomSource
import com.example.satelliteprojectcompose.data.room.source.SatelliteRoomSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TestDataModule {


    @Singleton
    @Provides
    fun provideSatelliteJsonSource(
        @ApplicationContext context: Context
    ): SatelliteJsonSource {
        return SatellitesJsonSourceImpl(context)
    }


    @Provides
    @Singleton
    fun provideRoomDataSource(
        satelliteDao: SatellitesDao
    ): SatelliteRoomSource =
        SatelliteRoomSourceImpl(satelliteDao)

    @Provides
    @Singleton
    fun provideSatellitesDatabase(
        @ApplicationContext context: Context
    ): SatellitesDatabase = Room
        .inMemoryDatabaseBuilder(
            context,
            SatellitesDatabase::class.java
        )
        .build()

    @Provides
    @Singleton
    fun provideSatelliteDao(satellitesDatabase: SatellitesDatabase): SatellitesDao =
        satellitesDatabase.satelliteDao()

}

