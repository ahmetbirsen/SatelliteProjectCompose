package com.example.satelliteprojectcompose.presentation.satellites

sealed class SatellitesEvent {
    data class Search(val searchString : String) : SatellitesEvent()
}