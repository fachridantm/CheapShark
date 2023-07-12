package com.ewide.test.fachridan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DealsItem(

    @field:SerializedName("dealID")
    val dealID: String,

    @field:SerializedName("price")
    val price: String? = null,

    @field:SerializedName("savings")
    val savings: String? = null,

    @field:SerializedName("storeID")
    val storeID: String,

    @field:SerializedName("retailPrice")
    val retailPrice: String? = null
)