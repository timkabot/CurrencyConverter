package com.app.exchangerates

import android.app.Application
import com.app.exchangerates.data.local.CurrencyDatabase
import com.app.exchangerates.di.createAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        initRoom()
    }

    private fun initRoom() {
        CurrencyDatabase.build(applicationContext)
    }


    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    createAppModule()
                )
            )
        }
    }
}