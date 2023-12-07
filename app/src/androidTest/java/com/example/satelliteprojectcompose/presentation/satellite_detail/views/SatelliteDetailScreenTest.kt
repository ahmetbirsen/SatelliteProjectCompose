package com.example.satelliteprojectcompose.presentation.satellite_detail.views

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.satelliteprojectcompose.dependency_injection.DataModule
import com.example.satelliteprojectcompose.dependency_injection.RepositoryModule
import com.example.satelliteprojectcompose.dependency_injection.UseCaseModule
import com.example.satelliteprojectcompose.presentation.MainActivity
import com.example.satelliteprojectcompose.presentation.Screen
import com.example.satelliteprojectcompose.presentation.satellite_detail.SatelliteDetailState
import com.example.satelliteprojectcompose.presentation.satellites.views.SatelliteScreen
import com.example.satelliteprojectcompose.presentation.ui.theme.SatelliteProjectComposeTheme
import com.example.satelliteprojectcompose.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
@UninstallModules(DataModule::class, RepositoryModule::class, UseCaseModule::class)
class SatelliteDetailScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            SatelliteProjectComposeTheme() {
                NavHost(
                    navController = navController,
                    startDestination = Screen.SatelliteListScreen.route
                ) {
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

    @Test
    fun showProgressWhenSatelliteDetailScreenLoading() {
        SatelliteDetailState(true, positions = emptyList())
        composeRule.onNodeWithTag(TestTags.SATELLITE_DETAIL_PROGRESS)
            .assertExists()
    }

    @Test
    fun testSatelliteDetailUIElements() {
        composeRule.onNodeWithTag(TestTags.SATELLITE_DETAIL_PROGRESS).assertIsDisplayed()
        composeRule.onNodeWithText("Starlink-1").assertIsDisplayed()
        composeRule.onNodeWithText("Height/Mass: ").assertIsDisplayed()
        composeRule.onNodeWithText("Cost: ").assertIsDisplayed()
    }

    @Test
    fun testSatelliteDetailError() {
        composeRule.onNodeWithText("Error Message").assertIsDisplayed()
    }

    @Test
    fun showErrorMessageWhenSatelliteDetailGetsErro() {
        SatelliteDetailState(false, error = "No detail found", positions = emptyList())
        composeRule.onNodeWithTag(TestTags.SATELLITE_DETAIL_PROGRESS)
            .assertExists()
    }

}