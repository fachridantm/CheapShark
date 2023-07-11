package com.ewide.test.fachridan.core.domain.model

data class Deal(
    val dealId: String,
    val gameId: String,
    val title: String,
    val normalPrice: String,
    val salePrice: String,
    val thumb: String,
)