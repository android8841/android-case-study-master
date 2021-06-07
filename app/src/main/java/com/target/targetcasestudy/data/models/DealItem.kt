package com.target.targetcasestudy.data.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "deals")
data class DealItem(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String,

    @Embedded
    @SerializedName("regular_price")
    val price: PriceData?,

    @ColumnInfo(name = "aisle")
    @SerializedName("aisle")
    val aisle: String,

    @ColumnInfo(name = "image_url")
    @SerializedName("image_url")
    val imageUrl: String
)