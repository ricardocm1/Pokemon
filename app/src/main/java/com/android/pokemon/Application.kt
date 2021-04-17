package com.android.pokemon

import android.app.Application
import com.android.pokemon.di.appModule
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }

}