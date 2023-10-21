package com.technical.test.fachridan.core.domain.usecase

import com.technical.test.fachridan.core.domain.model.Deal
import com.technical.test.fachridan.core.domain.repository.IMainRepository
import javax.inject.Inject

class DealsInteractor @Inject constructor(
    private val mainRepository: IMainRepository
) : DealsUseCase {
    override fun getListOfDeals() = mainRepository.getListOfDeals()
    override fun getSearchDeals(title: String) = mainRepository.getSearchDeals(title)
    override fun getSortListOfDeals(sortBy: String) = mainRepository.getSortListOfDeals(sortBy)
    override fun getFavoriteGames() = mainRepository.getFavoriteGames()
    override suspend fun setFavoriteGames(deal: Deal, newState: Boolean) =
        mainRepository.setFavoriteGames(deal, newState)
}