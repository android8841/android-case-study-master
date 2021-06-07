package com.target.targetcasestudy.data.models

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class PriceData(
    @ColumnInfo(name = "amount_in_cents")
    @SerializedName("amount_in_cents")
    val amount: Double,

    @ColumnInfo(name = "currency_symbol")
    @SerializedName("currency_symbol")
    val currencySymbol: String,

    @ColumnInfo(name = "display_string")
    @SerializedName("display_string")
    val displayValue: String
)
