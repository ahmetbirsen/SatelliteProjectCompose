package com.example.satelliteprojectcompose.dependency_injection

import android.content.Context
import androidx.room.Room
import com.example.projectplayground.data.file.source.SatelliteJsonSource
import com.example.projectplayground.data.file.source.SatellitesJsonSourceImpl
import com.example.satelliteprojectcompose.data.room.SatellitesDao
import com.example.satelliteprojectcompose.data.room.SatellitesDatabase
import com.example.satelliteprojectcompose.data.room.source.SatelliteRoomSource
import com.example.satelliteprojectcompose.data.room.source.SatelliteRoomSourceImpl
import com.example.satelliteprojectcompose.util.Constants.DATABASE_NAME
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

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
        .databaseBuilder(
            context,
            SatellitesDatabase::class.java,
            DATABASE_NAME
        )
        .build()

    @Provides
    @Singleton
    fun provideSatelliteDao(satellitesDatabase: SatellitesDatabase): SatellitesDao =
        satellitesDatabase.satelliteDao()
}