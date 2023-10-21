package com.technical.test.fachridan.core.domain.repository

import androidx.paging.PagingData
import com.technical.test.fachridan.core.data.source.Resource
import com.technical.test.fachridan.core.domain.model.Deal
import com.technical.test.fachridan.core.domain.model.GameDetails
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
    fun getListOfDeals(): Flow<Resource<PagingData<Deal>>>
    fun getGameDetails(gameId: String): Flow<Resource<GameDetails>>
    fun getSearchDeals(title: String): Flow<Resource<List<Deal>>>
    fun getSortListOfDeals(sortBy: String): Flow<Resource<PagingData<Deal>>>
    fun getFavoriteGames(): Flow<List<Deal>>
    suspend fun setFavoriteGames(deal: Deal, newState: Boolean)
}