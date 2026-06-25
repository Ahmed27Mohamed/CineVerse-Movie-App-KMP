package com.a2004256_ahmedmohamed.movieapp.bottom_bar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.a2004256_ahmedmohamed.movieapp.ui_ux.Background
import com.a2004256_ahmedmohamed.movieapp.ui_ux.Primary

@Composable
fun BottomBar(
    currentScreen: BottomBarScreens,
    onScreenSelected: (BottomBarScreens) -> Unit
) {
    val screens = listOf(
        BottomBarScreens.Home,
        BottomBarScreens.Discover,
        BottomBarScreens.AI,
        BottomBarScreens.Watchlist,
        BottomBarScreens.Stats
    )
    NavigationBar(
        containerColor = Background,
        tonalElevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .drawBehind {
                drawLine(
                    color = Color.DarkGray.copy(alpha = 3f),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 2f
                )
            }
    ) {
        screens.forEach { screen ->
            val selected = currentScreen == screen
            val scale by animateFloatAsState(
                targetValue = if (selected) 1.2f else 1f
            )
            val color by animateColorAsState(
                targetValue = if (selected) Primary else Color.Gray
            )
            NavigationBarItem(
                selected = selected,
                onClick = {
                    onScreenSelected(screen)
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title,
                        tint = color,
                        modifier = Modifier.graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                    )
                },
                label = {
                    Text(
                        screen.title,
                        color = color
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Primary,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Primary,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}