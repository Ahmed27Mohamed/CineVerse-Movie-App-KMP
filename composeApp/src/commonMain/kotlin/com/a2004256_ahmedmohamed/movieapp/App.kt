package com.a2004256_ahmedmohamed.movieapp

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import com.a2004256_ahmedmohamed.movieapp.bottom_bar.BottomBar
import com.a2004256_ahmedmohamed.movieapp.presentation.home.HomeScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.a2004256_ahmedmohamed.movieapp.bottom_bar.BottomBarScreens
import com.a2004256_ahmedmohamed.movieapp.presentation.ai.AIScreen
import com.a2004256_ahmedmohamed.movieapp.presentation.home.HomeScreenContent
import com.a2004256_ahmedmohamed.movieapp.presentation.home.HomeToolbar
import com.a2004256_ahmedmohamed.movieapp.presentation.home.HomeViewModel
import com.a2004256_ahmedmohamed.movieapp.presentation.profile.ProfileScreen
import com.a2004256_ahmedmohamed.movieapp.presentation.profile.ProfileViewModel
import com.a2004256_ahmedmohamed.movieapp.presentation.search.SearchScreen
import com.a2004256_ahmedmohamed.movieapp.presentation.stats.StatsScreen
import com.a2004256_ahmedmohamed.movieapp.presentation.watchlist.WatchlistScreen
import com.a2004256_ahmedmohamed.movieapp.ui_ux.CineVerseTheme
import okio.FileSystem
import org.koin.compose.koinInject

@OptIn(ExperimentalCoilApi::class)
@Composable
fun App() {
    setSingletonImageLoaderFactory { context ->
        getAsyncImageLoader(context)
    }
    CineVerseTheme {
        Navigator(
            screen = LauncherScreen()
        )
    }
}

class mainApp() : Screen {
    @Composable
    override fun Content() {
        mainAppContent()
    }
}

@Composable
fun mainAppContent() {
    var currentScreen by remember {
        mutableStateOf<BottomBarScreens>(
            BottomBarScreens.Home
        )
    }
    Scaffold(
        topBar = {
            val vm: ProfileViewModel = koinInject()
            val user by vm.user.collectAsState()
            HomeToolbar(
                onSearchClick = {
                    currentScreen = BottomBarScreens.Discover
                },
                onNotificationClick = {
                    currentScreen = BottomBarScreens.Notification
                },
                onProfileClick = {
                    currentScreen = BottomBarScreens.Profile
                },
                user = user
            )
        },
        bottomBar = {
            BottomBar(
                currentScreen = currentScreen,
                onScreenSelected = {
                    currentScreen = it
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            when (currentScreen) {
                BottomBarScreens.Home -> {
                    Navigator(
                        screen = HomeScreen()
                    )
                }
                BottomBarScreens.Discover -> {
                    Navigator(
                        screen = SearchScreen()
                    )
                }
                BottomBarScreens.AI -> {
                    Navigator(
                        screen =  AIScreen()
                    )
                }
                BottomBarScreens.Watchlist -> {
                    Navigator(
                        screen = WatchlistScreen()
                    )
                }
                BottomBarScreens.Stats -> {
                    Navigator(
                        screen = StatsScreen()
                    )
                }
                BottomBarScreens.Notification -> {
//                    NotificationScreen()
                }
                BottomBarScreens.Profile -> {
                    Navigator(
                        screen =  ProfileScreen()
                    )
                }
            }
        }
    }
}

fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context).memoryCachePolicy(CachePolicy.ENABLED).memoryCache {
        MemoryCache.Builder().maxSizePercent(context, 0.3).strongReferencesEnabled(true).build()
    }.diskCachePolicy(CachePolicy.ENABLED).networkCachePolicy(CachePolicy.ENABLED).diskCache {
        newDiskCache()
    }.crossfade(true).logger(DebugLogger()).build()

fun newDiskCache(): DiskCache {
    return DiskCache.Builder().directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
        .maxSizeBytes(1024L * 1024 * 1024) // 512MB
        .build()
}