package com.app.exchangerates.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currency(
    val rates: Map<String, Double>,
    @PrimaryKey
    val base: String)