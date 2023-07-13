package com.ewide.test.fachridan.core.domain.usecase

import com.ewide.test.fachridan.core.data.source.Resource
import com.ewide.test.fachridan.core.domain.model.Deal
import com.ewide.test.fachridan.core.domain.repository.IMainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DealsInteractor @Inject constructor(
    private val mainRepository: IMainRepository
) : DealsUseCase {
    override fun getListOfDeals() = mainRepository.getListOfDeals()
    override fun getSearchDeals(title: String): Flow<Resource<List<Deal>>> = mainRepository.getSearchDeals(title)
    override fun getSortListOfDeals(sortBy: String): Flow<Resource<List<Deal>>> = mainRepository.getSortListOfDeals(sortBy)
}