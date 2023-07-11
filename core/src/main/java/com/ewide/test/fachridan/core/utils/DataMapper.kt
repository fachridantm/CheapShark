package com.ewide.test.fachridan.core.utils

import com.ewide.test.fachridan.core.data.source.remote.response.DealsResponseItem
import com.ewide.test.fachridan.core.domain.model.Deal

object DataMapper {
    fun dealsItemToDeal(data: DealsResponseItem): Deal = Deal(
        id = data.dealID,
        name = data.title ?: "-",
        imageUrl = data.thumb
    )
}