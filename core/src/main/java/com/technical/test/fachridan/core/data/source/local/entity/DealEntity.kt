package com.technical.test.fachridan.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Deals")
data class DealEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "dealId")
    val dealId: String,

    @ColumnInfo(name = "gameId")
    val gameId: String,

    @ColumnInfo(name = "storeId")
    val storeId: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "normalPrice")
    val normalPrice: String,

    @ColumnInfo(name = "salePrice")
    val salePrice: String,

    @ColumnInfo(name = "thumb")
    val thumb: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
)