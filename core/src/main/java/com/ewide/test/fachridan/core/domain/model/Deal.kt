package com.ewide.test.fachridan.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Deal(
    val dealId: String,
    val gameId: String,
    val storeId: String,
    val title: String,
    val normalPrice: String,
    val salePrice: String,
    val thumb: String,
) : Parcelable