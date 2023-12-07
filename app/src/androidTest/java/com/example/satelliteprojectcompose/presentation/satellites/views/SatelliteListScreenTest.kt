package com.example.satelliteprojectcompose.presentation.satellites.views

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projectplayground.domain.model.Satellite
import com.example.satelliteprojectcompose.dependency_injection.DataModule
import com.example.satelliteprojectcompose.dependency_injection.RepositoryModule
import com.example.satelliteprojectcompose.dependency_injection.UseCaseModule
import com.example.satelliteprojectcompose.presentation.MainActivity
import com.example.satelliteprojectcompose.presentation.Screen
import com.example.satelliteprojectcompose.presentation.satellite_detail.views.SatelliteDetailScreen
import com.example.satelliteprojectcompose.presentation.satellites.SatellitesState
import com.example.satelliteprojectcompose.presentation.ui.theme.SatelliteProjectComposeTheme
import com.example.satelliteprojectcompose.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SatelliteListScreenTest {
    @HiltAndroidTest
    @UninstallModules(DataModule::class, RepositoryModule::class, UseCaseModule::class)
    class SatelliteListScreenTest {

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
                        composable(route = "${Screen.SatelliteDetailScreen.route}/{satelliteId}/{satelliteName}",
                            arguments = listOf(
                                navArgument("satelliteId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument("satelliteName") {
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
        fun testSatelliteScreenUIElements() {
            composeRule.onNodeWithTag(TestTags.SATELLITE_SEARCH_BAR_COMPONENT).assertExists()
            composeRule.onNodeWithTag(TestTags.SATELLITE_SEARCH_BAR_COMPONENT).assertIsDisplayed()
        }


        @Test
        fun testSearchFunctionality() {
            composeRule.onNodeWithTag(TestTags.SATELLITE_SEARCH_BAR_COMPONENT)
                .performTextInput("Dragon-1")
        }

        @Test
        fun showProgressWhenSatlliteScreenLoading() {
            SatellitesState(true)
            composeRule.onNodeWithTag(TestTags.SATELLITE_LIST_PROGRESS)
                .assertExists()
        }

        @Test
        fun showLazyColumnWhenGetData() {

            SatellitesState(satellites = listOf(Satellite(false, 1, "Ahmet")))

            composeRule.onNodeWithTag(TestTags.SATELLITE_LAZY_COLUMN)
                .assertExists()
                .assertHasClickAction()
        }
    }
}