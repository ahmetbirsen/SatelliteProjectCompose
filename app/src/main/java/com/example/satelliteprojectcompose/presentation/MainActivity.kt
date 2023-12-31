package com.example.satelliteprojectcompose.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.satelliteprojectcompose.presentation.satellite_detail.views.SatelliteDetailScreen
import com.example.satelliteprojectcompose.presentation.satellites.views.SatelliteScreen
import com.example.satelliteprojectcompose.presentation.ui.theme.SatelliteProjectComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SatelliteProjectComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.SatelliteListScreen.route ) {
                        composable(route = Screen.SatelliteListScreen.route) {
                            SatelliteScreen(navController = navController)
                        }

                        composable(route = "${Screen.SatelliteDetailScreen.route}/{satelliteId}/{satelliteName}", arguments = listOf(
                            navArgument("satelliteId"){
                                type = NavType.IntType
                                defaultValue = -1
                            },
                            navArgument("satelliteName"){
                                type = NavType.StringType
                            }
                        )) {
                            val satelliteId = remember {
                                it.arguments?.getInt("satelliteId")
                            }
                            val satelliteName = remember {
                                it.arguments?.getString("satelliteName")
                            }
                            SatelliteDetailScreen(
                                satelliteId = satelliteId ?: 0,
                                satelliteName = satelliteName ?: "Bulunamadı"
                            )
                        }
                    }
                }
            }
        }
    }
}

