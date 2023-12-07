package com.example.satelliteprojectcompose.presentation.satellites.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.satelliteprojectcompose.presentation.Screen
import com.example.satelliteprojectcompose.presentation.satellites.SatellitesEvent
import com.example.satelliteprojectcompose.presentation.satellites.SatellitesViewModel
import com.example.satelliteprojectcompose.util.TestTags

@Composable
fun SatelliteScreen(
    navController: NavController,
    viewModel: SatellitesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            SatelliteSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .testTag(TestTags.SATELLITE_SEARCH_BAR_COMPONENT),
                onSearch = {
                    viewModel.onEvent(SatellitesEvent.Search(it))
                }
            )
            LazyColumn(
                modifier = Modifier,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                items(state.satellites) { satellite ->
                    SatelliteListRow(
                        satellite = satellite,
                        isLastItem = satellite.id == state.satellites.size,
                        onItemClick = {
                            navController.navigate("${Screen.SatelliteDetailScreen.route}/${satellite.id}/${satellite.name}")
                        })
                }
            }

        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .align(Alignment.Center)
                    .testTag(TestTags.SATELLITE_LIST_ERROR)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .testTag("SATELLITE_LIST_PROGRESS")
            )
        }
    }
}