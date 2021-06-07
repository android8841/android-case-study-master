package com.target.targetcasestudy.data.models

import com.google.gson.annotations.SerializedName

data class Deals(

    @SerializedName("products")
    val products: List<DealItem>
)
