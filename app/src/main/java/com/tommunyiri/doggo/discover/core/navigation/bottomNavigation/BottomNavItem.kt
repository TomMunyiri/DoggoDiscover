package com.tommunyiri.doggo.discover.core.navigation.bottomNavigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.tommunyiri.doggo.discover.R
import com.tommunyiri.doggo.discover.core.navigation.NavDestinations.Main.FAVORITES
import com.tommunyiri.doggo.discover.core.navigation.NavDestinations.Main.HOME
import com.tommunyiri.doggo.discover.core.utils.ResourcesProviderHelper
import com.tommunyiri.doggo.discover.presentation.components.AppIcons

sealed class BottomNavItem {
    object Home : Item(
        path = HOME,
        title = ResourcesProviderHelper.getString(R.string.nav_title_home),
        icon = AppIcons.Home,
    )

    object Favorites : Item(
        path = FAVORITES,
        title = ResourcesProviderHelper.getString(R.string.nav_title_favorite),
        icon = AppIcons.Favorite,
    )
}

open class Item(val path: String, val title: String, val icon: ImageVector)
