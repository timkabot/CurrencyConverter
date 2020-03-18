package com.app.exchangerates.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData
import com.app.exchangerates.data.local.CurrencyDatabase
import com.app.exchangerates.data.server.CurrencyApi
import com.app.exchangerates.domain.entities.Currency
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class CurrencyRepository(
    private val currencyApi: CurrencyApi,
    db: CurrencyDatabase,
    private val context: Context
) : ICurrencyRepository {
    private val currencyDisposables = CompositeDisposable()
    private val dao = db.currencyDao()
    override fun getLatestCurrency(): LiveData<List<Currency>> {

        currencyApi.getDefaultCurrency()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe { result ->
                dao.insertCurrencies(result.copy())
            }.addTo(currencyDisposables)

        return dao.getAll()
    }

    private fun hasInternet(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    override fun getCurrencyWithBase(base: String): LiveData<List<Currency>> {
        if (hasInternet()) {
            currencyApi.getCurrency(base)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { currency ->
                    dao.insertCurrencies(currency.copy())
                }
                .addTo(currencyDisposables)
        }
        return dao.getCurrency(base)
    }
}