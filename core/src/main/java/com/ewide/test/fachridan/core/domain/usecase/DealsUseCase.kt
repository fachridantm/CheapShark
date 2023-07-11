package com.ewide.test.fachridan.core.domain.usecase

import androidx.paging.PagingData
import com.ewide.test.fachridan.core.data.source.Resource
import com.ewide.test.fachridan.core.domain.model.Deal
import kotlinx.coroutines.flow.Flow

interface DealsUseCase {
    fun getListOfDeals() : Flow<Resource<PagingData<Deal>>>
}