package com.a2004256_ahmedmohamed.movieapp.bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
sealed class BottomBarScreens(val title: String, val icon: ImageVector) {
    object Home : BottomBarScreens("Home", Icons.Default.Home)
    object Discover : BottomBarScreens("Discover", Icons.Default.Search)
    object AI : BottomBarScreens("AI", Icons.Default.AutoAwesome)
    object Watchlist : BottomBarScreens("Watchlist", Icons.Default.Bookmark)
    object Stats : BottomBarScreens("Stats", Icons.Default.BarChart)
    object Profile : BottomBarScreens("", Icons.Default.Home)
    object Notification : BottomBarScreens("", Icons.Default.Home)
}