package com.ewide.test.fachridan.core.domain.model

data class Store(
    val storeId: String,
    val storeName: String,
    val storeLogo: String,
    val normalPrice: String,
    val salePrice: String,
    val isActive: Boolean,
)
