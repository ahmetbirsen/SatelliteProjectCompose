package com.example.satelliteprojectcompose.presentation

sealed class Screen(val route : String) {
    object SatelliteListScreen : Screen("satellite_list")
    object SatelliteDetailScreen : Screen("satellite_detail")
}
