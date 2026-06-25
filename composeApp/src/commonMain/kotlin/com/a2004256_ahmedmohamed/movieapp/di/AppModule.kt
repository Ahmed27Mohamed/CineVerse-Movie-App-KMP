package com.a2004256_ahmedmohamed.movieapp.di

import com.a2004256_ahmedmohamed.movieapp.data.remote.HttpClientFactory
import com.a2004256_ahmedmohamed.movieapp.data.remote.MovieApi
import com.a2004256_ahmedmohamed.movieapp.data.repository.MovieRepositoryImpl
import com.a2004256_ahmedmohamed.movieapp.domain.repository.MovieRepository
import com.a2004256_ahmedmohamed.movieapp.presentation.ai.AIRepository
import com.a2004256_ahmedmohamed.movieapp.presentation.ai.AIViewModel
import com.a2004256_ahmedmohamed.movieapp.presentation.ai.OpenAIClient
import com.a2004256_ahmedmohamed.movieapp.presentation.home.HomeViewModel
import com.a2004256_ahmedmohamed.movieapp.presentation.login_and_register.AuthRepository
import com.a2004256_ahmedmohamed.movieapp.presentation.login_and_register.AuthRepositoryImpl
import com.a2004256_ahmedmohamed.movieapp.presentation.login_and_register.FirebaseAuthService
import com.a2004256_ahmedmohamed.movieapp.presentation.onboarding.SettingsManager
import com.a2004256_ahmedmohamed.movieapp.presentation.profile.ProfileViewModel
import com.a2004256_ahmedmohamed.movieapp.presentation.search.SearchViewModel
import com.a2004256_ahmedmohamed.movieapp.presentation.stats.StatsViewModel
import com.a2004256_ahmedmohamed.movieapp.presentation.watchlist.WatchlistRepository
import com.a2004256_ahmedmohamed.movieapp.presentation.watchlist.WatchlistViewModel
import com.russhwolf.settings.Settings
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.database.database
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create() }
    single { MovieApi(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single { HomeViewModel(get()) }
    single { SearchViewModel(get()) }
    single { WatchlistRepository() }
    single { WatchlistViewModel(repository = get()) }
    single<Settings> { Settings() }
    single { SettingsManager(get()) }
    single { AIRepository(get()) }
    single { OpenAIClient(get()) }
    single { AIViewModel(get()) }
    single { ProfileViewModel(get(), get()) }
    single { StatsViewModel(get(), get()) }
    single { Firebase.auth }
    single { Firebase.database.reference() }
    single { FirebaseAuthService(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}