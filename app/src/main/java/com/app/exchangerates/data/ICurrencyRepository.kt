package com.app.exchangerates.data

import androidx.lifecycle.LiveData
import com.app.exchangerates.domain.entities.Currency
import io.reactivex.Maybe
import io.reactivex.Single

interface ICurrencyRepository {
    fun getLatestCurrency() : LiveData<List<Currency>>
    fun getCurrencyWithBase(base: String): LiveData<List<Currency>>
}