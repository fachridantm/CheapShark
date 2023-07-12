package com.ewide.test.fachridan.core.utils

import com.ewide.test.fachridan.core.data.source.remote.response.DealsResponseItem
import com.ewide.test.fachridan.core.data.source.remote.response.GameDetailsResponse
import com.ewide.test.fachridan.core.domain.model.CheapestPrice
import com.ewide.test.fachridan.core.domain.model.Deal
import com.ewide.test.fachridan.core.domain.model.GameDetails
import com.ewide.test.fachridan.core.domain.model.GameInfo
import com.ewide.test.fachridan.core.domain.model.Store

object DataMapper {
    fun dealsItemToDeal(data: DealsResponseItem): Deal = Deal(
        dealId = data.dealID,
        gameId = data.gameID,
        storeId = "",
        title = data.title ?: "-",
        normalPrice = data.normalPrice ?: "-",
        salePrice = data.salePrice ?: "-",
        thumb = data.thumb
    )

    fun gameDetailsResponseToGameDetails(data: GameDetailsResponse): GameDetails = GameDetails(
        cheapestPrice = CheapestPrice(
            price = data.cheapestPriceEver.price ?: "-",
            date = data.cheapestPriceEver.date,
        ),
        deals = data.deals.map {
            Store(
                storeId = it.storeID,
                storeLogo = it.storeID,
                normalPrice = it.retailPrice ?: "-",
                salePrice = it.price ?: "-",
            )
        },
        gameInfo = GameInfo(
            steamAppId = data.info?.steamAppID ?: "-",
            thumb = data.info?.thumb ?: "-",
            title = data.info?.title ?: "-",
        ),
    )
}