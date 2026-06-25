package com.a2004256_ahmedmohamed.movieapp.di

import org.koin.core.context.startKoin

fun initKoin() {

    startKoin {
        modules(appModule)
    }

}