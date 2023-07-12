package com.ewide.test.fachridan.core.domain.repository

import androidx.paging.PagingData
import com.ewide.test.fachridan.core.data.source.Resource
import com.ewide.test.fachridan.core.domain.model.Deal
import com.ewide.test.fachridan.core.domain.model.GameDetails
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
    fun getListOfDeals(): Flow<Resource<PagingData<Deal>>>
    fun getGameDetails(gameId: String): Flow<Resource<GameDetails>>
}