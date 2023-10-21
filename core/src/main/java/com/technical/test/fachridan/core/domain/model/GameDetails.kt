package com.technical.test.fachridan.core.domain.model

data class GameDetails(
    val cheapestPrice: CheapestPrice,
    val deals: List<Store>,
    val gameInfo: GameInfo
)
