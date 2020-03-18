package com.app.exchangerates.domain.interactors

import androidx.lifecycle.MutableLiveData
import com.app.exchangerates.data.CurrencyRepository
import com.app.exchangerates.domain.entities.Currency


class CurrencyInteractor(private val currencyRepository: CurrencyRepository) {
    val latestCurrency = MutableLiveData<List<Currency>>()

    fun updateCurrency(base: String) {
        currencyRepository.getCurrencyWithBase(base).observeForever {
            if (it.isNotEmpty()) latestCurrency.postValue(it)
        }
    }
}