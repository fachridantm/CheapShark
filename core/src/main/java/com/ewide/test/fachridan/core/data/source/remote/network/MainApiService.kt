package com.ewide.test.fachridan.core.data.source.remote.network

import com.ewide.test.fachridan.core.data.source.remote.response.DealsResponseItem
import com.ewide.test.fachridan.core.data.source.remote.response.GameDetailsResponse
import com.ewide.test.fachridan.core.utils.Constants.DEFAULT
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApiService {
    @GET("deals")
    suspend fun getListOfDeals(
        @Query("pageSize") pageSize: Int = 20,
        @Query("pageNumber") pageNumber: Int = 0,
    ): List<DealsResponseItem>

    @GET("games")
    suspend fun getGameDetails(
        @Query("id") gameId: String,
    ): GameDetailsResponse

    @GET("deals")
    suspend fun getSearchDeals(
        @Query("title") title: String,
    ): List<DealsResponseItem>

    @GET("deals")
    suspend fun getSortListOfDeals(
        @Query("sortBy") sortBy: String = DEFAULT,
    ): List<DealsResponseItem>
}