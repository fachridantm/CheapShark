package com.ewide.test.fachridan.core.domain.usecase

import com.ewide.test.fachridan.core.domain.repository.IMainRepository
import javax.inject.Inject

class StoresInteractor @Inject constructor(
    private val mainRepository: IMainRepository
) : StoresUseCase {
    override fun getGameDetails(gameId: String) = mainRepository.getGameDetails(gameId)
}