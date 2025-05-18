package com.tommunyiri.doggo.discover.core.navigation.graphs

import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tommunyiri.doggo.discover.core.navigation.NavDestinations
import com.tommunyiri.doggo.discover.core.navigation.NavDestinations.Main.MAIN
import com.tommunyiri.doggo.discover.core.navigation.bottomNavigation.BottomNavItem
import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.presentation.screens.dogdetails.DogDetailsScreen
import com.tommunyiri.doggo.discover.presentation.screens.favorites.FavoritesScreen
import com.tommunyiri.doggo.discover.presentation.screens.home.HomeScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
    onThemeUpdated: () -> Unit,
) {
    navigation(
        startDestination = BottomNavItem.Home.path,
        route = MAIN,
    ) {
        composable(
            BottomNavItem.Home.path,
            enterTransition = { slideInHorizontally() },
            exitTransition = { fadeOut() },
        ) {
            HomeScreen(
                onDogClick = { dogInfo ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("dogInfo", dogInfo)
                    navController.navigate(NavDestinations.Main.DETAILS)
                },
            )
        }
        composable(
            BottomNavItem.Favorites.path,
            enterTransition = { slideInHorizontally() },
            exitTransition = { fadeOut() },
        ) {
            FavoritesScreen(
                onDogClick = { dogInfo ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("dogInfo", dogInfo)
                    navController.navigate(NavDestinations.Main.DETAILS)
                },
            )
        }
        composable(
            NavDestinations.Main.DETAILS,
            enterTransition = { slideInHorizontally() },
            exitTransition = { fadeOut() },
        ) {
            val dogInfo = navController.previousBackStackEntry?.savedStateHandle?.get<DogInfo>("dogInfo")
            DogDetailsScreen(dogInfo = dogInfo, navController)
        }
    }
}
