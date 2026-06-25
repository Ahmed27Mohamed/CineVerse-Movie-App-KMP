package com.a2004256_ahmedmohamed.movieapp.ui_ux

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

@Composable
fun CineVerseTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Background,
            surface = Surface,
            primary = Primary,
            secondary = Secondary,
            onBackground = TextPrimary,
            onSurface = TextPrimary
        ),
        typography = AppTypography,
        content = content
    )
}