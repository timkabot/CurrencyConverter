package com.app.exchangerates.presentation.exchangeRate.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.exchangerates.domain.entities.Currency
import com.app.exchangerates.domain.interactors.CurrencyInteractor
import com.app.exchangerates.utils.round


class MainViewModel(private val currencyInteractor: CurrencyInteractor) : ViewModel() {
    val exchangedEvents = MutableLiveData<String>()
    private var currentCurrency: Currency? = null

    init {
        currencyInteractor.latestCurrency.observeForever {
            currentCurrency = it[0]
        }
    }

    fun updateCurrency(base: String) {
        currencyInteractor.updateCurrency(base)
    }

    fun exchange(exchangeRateFrom: String, exchangeRateTo: String, value: String) {

        if (currentCurrency != null && currentCurrency!!.base == exchangeRateFrom) {
            currentCurrency!!.rates[exchangeRateTo]?.let {
                exchangedEvents.postValue(
                    (it * value.toDouble()).round(2).toString()
                )
            }
        } else {
            exchangedEvents.postValue("No data")
        }
    }

}