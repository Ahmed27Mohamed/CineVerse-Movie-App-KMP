package com.a2004256_ahmedmohamed.movieapp.presentation.onboarding

import com.russhwolf.settings.Settings

class SettingsManager(
    private val settings: Settings
) {

    companion object {
        private const val ONBOARDING_KEY = "onboarding_done"
    }

    fun isOnboardingDone(): Boolean {
        return settings.getBoolean(
            ONBOARDING_KEY,
            false
        )
    }

    fun setOnboardingDone() {
        settings.putBoolean(
            ONBOARDING_KEY,
            true
        )
    }
}