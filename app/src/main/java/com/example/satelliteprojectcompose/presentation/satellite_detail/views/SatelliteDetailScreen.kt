package com.example.satelliteprojectcompose.presentation.satellite_detail.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.satelliteprojectcompose.presentation.satellite_detail.SatelliteDetailViewModel
import com.example.satelliteprojectcompose.util.TestTags
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SatelliteDetailScreen(
    satelliteId: Int,
    satelliteName: String,
    satelliteDetailViewModel: SatelliteDetailViewModel = hiltViewModel()
) {
    val state = satelliteDetailViewModel.state.value
    var positionsState = satelliteDetailViewModel.positionsState.value


    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(true) {
        val positionsSize = positionsState.satellitePositions?.positions?.size ?: 0
        if (positionsSize > 0) {
            repeat(positionsSize) {
                delay(1000)
                currentIndex = (currentIndex + 1) % positionsSize
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            state.satellite?.let {
                Text(
                    text = satelliteName,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp

                )

                Text(
                    text = it.first_flight,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Height/Mass: ",
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${it.height}/${it.mass}",
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Cost: ",
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${it.cost_per_launch}",
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Last Position: ",
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    if (state.positions?.isNotEmpty() == true) {
                        Text(
                            text = "(${state.positions?.get(currentIndex)?.posX} , ${state.positions?.get(currentIndex)?.posY})",
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                    } else {
                        Text(
                            text = "No positions available",
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                    }
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
                    .testTag(TestTags.SATELLITE_DETAIL_ERROR)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier
                .align(Alignment.Center)
                .testTag(TestTags.SATELLITE_DETAIL_PROGRESS)
            )
        }
    }
}