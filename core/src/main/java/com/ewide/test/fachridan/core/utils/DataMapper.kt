package com.ewide.test.fachridan.core.utils

import com.ewide.test.fachridan.core.data.source.remote.response.DealsResponseItem
import com.ewide.test.fachridan.core.domain.model.Deal

object DataMapper {
    fun dealsItemToDeal(data: DealsResponseItem): Deal = Deal(
        dealId = data.dealID,
        gameId = data.gameID,
        title = data.title ?: "-",
        normalPrice = data.normalPrice ?: "-",
        salePrice = data.salePrice ?: "-",
        thumb = data.thumb
    )
}