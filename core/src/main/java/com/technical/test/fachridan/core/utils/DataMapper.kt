package com.technical.test.fachridan.core.utils

import com.technical.test.fachridan.core.data.source.local.entity.DealEntity
import com.technical.test.fachridan.core.data.source.remote.response.DealsResponseItem
import com.technical.test.fachridan.core.data.source.remote.response.GameDetailsResponse
import com.technical.test.fachridan.core.domain.model.CheapestPrice
import com.technical.test.fachridan.core.domain.model.Deal
import com.technical.test.fachridan.core.domain.model.GameDetails
import com.technical.test.fachridan.core.domain.model.GameInfo
import com.technical.test.fachridan.core.domain.model.Store

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

    fun dealEntityToDeal(data: List<DealEntity>): List<Deal> = data.map {
        Deal(
            dealId = it.dealId,
            gameId = it.gameId,
            storeId = it.storeId,
            title = it.title,
            normalPrice = it.normalPrice,
            salePrice = it.salePrice,
            thumb = it.thumb,
            isFavorite = it.isFavorite,
        )
    }

    fun dealToDealEntity(data: Deal): DealEntity = DealEntity(
        dealId = data.dealId,
        gameId = data.gameId,
        storeId = data.storeId,
        title = data.title,
        normalPrice = data.normalPrice,
        salePrice = data.salePrice,
        thumb = data.thumb,
        isFavorite = data.isFavorite,
    )
}