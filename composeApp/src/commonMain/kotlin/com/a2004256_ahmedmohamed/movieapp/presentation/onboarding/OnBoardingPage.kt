package com.a2004256_ahmedmohamed.movieapp.presentation.onboarding

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

data class OnBoardingPage(
    val title: String,
    val description: String,
    val image: DrawableResource,
    val color: Color
)