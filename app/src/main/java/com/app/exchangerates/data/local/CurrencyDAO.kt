package com.app.exchangerates.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.exchangerates.domain.entities.Currency

@Dao
interface CurrencyDAO {

    @Query("SELECT * FROM Currency")
    fun getAll(): LiveData<List<Currency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencies(currency: Currency): Long

    @Query("SELECT * FROM Currency WHERE base = :base")
    fun getCurrency(base: String): LiveData<List<Currency>>
}