package com.example.satelliteprojectcompose.presentation.satellites

//sealed class yapma sebebimiz o sınıftan bir instance oluşturmayıp tamamen içeriğindekileri kullanacak olmamız
sealed class SatellitesEvent {
    data class Search(val searchString : String) : SatellitesEvent()
}