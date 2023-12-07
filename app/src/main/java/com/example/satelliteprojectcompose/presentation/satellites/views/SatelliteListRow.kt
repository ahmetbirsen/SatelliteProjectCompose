package com.example.satelliteprojectcompose.presentation.satellites.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.projectplayground.domain.model.Satellite


@Composable
fun SatelliteListRow(
    satellite: Satellite,
    isLastItem: Boolean,
    onItemClick: (Satellite) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(satellite)
            }
            .padding(10.dp),

        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .background(
                    color = if (satellite.active) Color.Green else Color.Red,
                    shape = CircleShape
                ),
            imageVector = if (satellite.active) Icons.Filled.Check else Icons.Filled.Close,
            tint = Color.White,
            contentDescription = "Active Icon"
        )

        Spacer(modifier = Modifier.width(24.dp))

        Column(
            modifier = Modifier
                .align(CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = satellite.name,
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                color = if (satellite.active) Color.Black else Color.LightGray,
                textAlign = TextAlign.Center
            )
            Text(
                text = if (satellite.active) "Active" else "Passive",
                style = MaterialTheme.typography.h5,
                overflow = TextOverflow.Ellipsis,
                color = if (satellite.active) Color.Black else Color.LightGray,
                textAlign = TextAlign.Center
            )

            if (!isLastItem) Divider()
        }
    }
}

