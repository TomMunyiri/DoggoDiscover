package com.tommunyiri.doggo.discover.presentation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tommunyiri.doggo.discover.R
import com.tommunyiri.doggo.discover.core.navigation.NavDestinations.Main.DETAILS
import com.tommunyiri.doggo.discover.core.navigation.NavDestinations.Main.MAIN
import com.tommunyiri.doggo.discover.core.navigation.graphs.mainNavGraph
import com.tommunyiri.doggo.discover.presentation.components.BottomNavigationBar
import com.tommunyiri.doggo.discover.presentation.components.CenterAlignedTopAppBarComponent

/**
 * Composable function that represents the main screen of the application.
 *
 * @param navController The navigation controller used for handling navigation between screens.
 */

@Composable
fun MainScreen(
    navController: NavHostController,
    onThemeUpdated: () -> Unit,
) {
    val homeTitle: String = stringResource(R.string.nav_title_home)
    var centerAlignedTopBarTitle by rememberSaveable { mutableStateOf(homeTitle) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val noBottomBar = listOf(DETAILS)

    Scaffold(
        bottomBar = {
            if (currentRoute !in noBottomBar) {
                BottomAppBar {
                    BottomNavigationBar(
                        navController = navController,
                        bottomNavBarClicked = { centerAlignedTopBarTitle = it },
                    )
                }
            }
        },
        topBar = {
            if (currentRoute !in noBottomBar) {
                CenterAlignedTopAppBarComponent(centerAlignedTopBarTitle)
            }
        },
    ) { contentPadding ->
        CompositionLocalProvider(LocalMainContentPadding provides contentPadding) {
            NavHost(
                navController,
                startDestination = MAIN,
            ) {
                mainNavGraph(navController, onThemeUpdated)
            }
        }
    }
}
