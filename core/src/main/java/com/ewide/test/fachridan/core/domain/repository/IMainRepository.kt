package com.ewide.test.fachridan.core.domain.repository

import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData
import com.ewide.test.fachridan.core.data.source.Resource
import com.ewide.test.fachridan.core.domain.model.Deal

interface IMainRepository {
    fun getListOfDeals(): Flow<Resource<PagingData<Deal>>>
}