package com.ewide.test.fachridan.core.domain.usecase

import com.ewide.test.fachridan.core.domain.repository.IMainRepository
import javax.inject.Inject

class DealsInteractor @Inject constructor(
    private val mainRepository: IMainRepository
) : DealsUseCase {
    override fun getListOfDeals() = mainRepository.getListOfDeals()
}