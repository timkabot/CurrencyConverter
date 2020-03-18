package com.app.exchangerates.di

import com.app.exchangerates.data.CurrencyRepository
import com.app.exchangerates.data.local.CurrencyDatabase
import com.app.exchangerates.data.server.CurrencyApi
import com.app.exchangerates.domain.interactors.CurrencyInteractor
import com.app.exchangerates.presentation.exchangeRate.viewModel.MainViewModel
import com.app.exchangerates.utils.Constants
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun createAppModule(baseUrl: String = Constants.BASEURL) = module {
    single { CurrencyRepository(get(), get(), get()) }
    single { CurrencyApi.create(baseUrl) }
    single { CurrencyInteractor(get()) }
    viewModel { MainViewModel(get()) }
    single { CurrencyDatabase.build(get()) }
}
