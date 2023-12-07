package com.example.satelliteprojectcompose.dependency_injection

import android.content.Context
import com.example.projectplayground.data.file.source.SatelliteJsonSource
import com.example.projectplayground.data.file.source.SatellitesJsonSourceImpl
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
}