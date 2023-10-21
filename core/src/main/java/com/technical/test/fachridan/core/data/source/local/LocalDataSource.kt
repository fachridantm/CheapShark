package com.technical.test.fachridan.core.data.source.local

import com.technical.test.fachridan.core.data.source.local.entity.DealEntity
import com.technical.test.fachridan.core.data.source.local.room.DealDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val dealDao: DealDao,
) {
    fun getFavoriteGames(): Flow<List<DealEntity>> = dealDao.getFavoriteGames()
    suspend fun setFavoriteGames(dealEntity: DealEntity, newState: Boolean) {
        if (newState) {
            dealEntity.isFavorite = newState
            dealDao.apply {
                insertGame(dealEntity)
                updateGame(dealEntity)
            }
        } else {
            dealDao.deleteGame(dealEntity)
        }
    }
}