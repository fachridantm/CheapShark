package com.ewide.test.fachridan.core.data.source.remote.network

import com.ewide.test.fachridan.core.data.source.remote.response.DealsResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApiService {
    @GET("deals")
    suspend fun getListOfDeals(
        @Query("pageSize") pageSize: Int = 20,
        @Query("pageNumber") pageNumber: Int = 0,
    ): List<DealsResponseItem>
}