package com.a2004256_ahmedmohamed.movieapp

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.a2004256_ahmedmohamed.movieapp.presentation.home.HomeScreen
import com.a2004256_ahmedmohamed.movieapp.presentation.login_and_register.LoginScreen
import com.a2004256_ahmedmohamed.movieapp.presentation.onboarding.OnBoardingScreen
import com.a2004256_ahmedmohamed.movieapp.presentation.onboarding.SettingsManager
import dev.gitlive.firebase.auth.FirebaseAuth
import org.koin.compose.koinInject

class LauncherScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val auth: FirebaseAuth = koinInject()
        val settingsManager: SettingsManager = koinInject()

        LaunchedEffect(Unit) {
            if (settingsManager.isOnboardingDone()) {
                val user = auth.currentUser
                if (user != null) {
                    navigator.replace(mainApp())
                } else {
                    navigator.replace(LoginScreen())
                }
            } else {
                navigator.replace(
                    OnBoardingScreen(
                        onFinish = {
                            settingsManager.setOnboardingDone()
                            navigator.replace(
                                LoginScreen()
                            )
                        }
                    )
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}