package com.app.exchangerates.data.server

import com.app.exchangerates.domain.entities.Currency
import com.app.exchangerates.utils.Constants
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("latest")
    fun getDefaultCurrency(): Single<Currency>

    @GET("latest")
    fun getCurrency(@Query("base") base: String): Observable<Currency>

    companion object Factory {
        fun create(baseUrl: String = Constants.BASEURL): CurrencyApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(CurrencyApi::class.java)
        }
    }
}