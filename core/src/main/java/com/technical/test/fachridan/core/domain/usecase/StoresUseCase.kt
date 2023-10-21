package com.technical.test.fachridan.core.domain.usecase

import com.technical.test.fachridan.core.data.source.Resource
import com.technical.test.fachridan.core.domain.model.GameDetails
import kotlinx.coroutines.flow.Flow

interface StoresUseCase {
    fun getGameDetails(gameId: String): Flow<Resource<GameDetails>>
}