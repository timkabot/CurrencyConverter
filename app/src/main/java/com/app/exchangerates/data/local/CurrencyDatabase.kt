package com.app.exchangerates.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.exchangerates.domain.entities.Currency
import com.app.exchangerates.utils.TypeConverter

@Database(entities = [Currency::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class CurrencyDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            CurrencyDatabase::class.java,
            "currency-db.db"
        ).build()
    }

    abstract fun currencyDao(): CurrencyDAO
}