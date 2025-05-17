package com.tommunyiri.doggo.discover.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tommunyiri.doggo.discover.R
import com.tommunyiri.doggo.discover.core.navigation.bottomNavigation.BottomNavItem

/**
 * Composable function that represents the bottom navigation bar of the application.
 *
 * @param navController The navigation controller used for handling navigation between screens.
 */

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    bottomNavBarClicked: (String) -> Unit,
) {
    val bottomNavItems =
        listOf(
            BottomNavItem.Home,
            BottomNavItem.Favorites
        )

    val backStackEntry by navController.currentBackStackEntryAsState()

    when (backStackEntry?.destination?.route) {
        BottomNavItem.Home.path -> {
            bottomNavBarClicked.invoke(stringResource(R.string.app_name))
        }

        BottomNavItem.Favorites.path -> {
            bottomNavBarClicked.invoke(stringResource(R.string.nav_title_favorite))
        }

        else -> {
            bottomNavBarClicked.invoke(stringResource(R.string.nav_title_home))
        }
    }

    val currentRoute = backStackEntry?.destination?.route
    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title, fontWeight = FontWeight.SemiBold) },
                selected = currentRoute == item.path,
                onClick = {
                    navController.navigate(item.path) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}
