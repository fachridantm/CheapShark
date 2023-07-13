package com.ewide.test.fachridan.core.data.source.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ewide.test.fachridan.core.data.source.remote.network.ApiResponse
import com.ewide.test.fachridan.core.data.source.remote.network.MainApiService
import com.ewide.test.fachridan.core.data.source.remote.paging.DealsPagingSource
import com.ewide.test.fachridan.core.data.source.remote.response.DealsResponseItem
import com.ewide.test.fachridan.core.data.source.remote.response.GameDetailsResponse
import com.ewide.test.fachridan.core.utils.getErrorMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Singleton
class RemoteDataSource @Inject constructor(private val mainApiService: MainApiService) {
    suspend fun getListOfDeals(): Flow<ApiResponse<PagingData<DealsResponseItem>>> =
        flow {
            Pager(
                config = PagingConfig(
                    pageSize = 50,
                ),
                pagingSourceFactory = { DealsPagingSource(mainApiService) }
            ).flow
                .map { ApiResponse.Success(it) }
                .catch { e ->
                    when (e) {
                        is HttpException -> {
                            val message = when (e.code()) {
                                401 -> "Unauthorized"
                                403 -> "Forbidden"
                                404 -> "Not Found"
                                else -> e.getErrorMessage().toString()
                            }
                            emit(ApiResponse.Error(message))
                        }

                        is UnknownHostException -> {
                            emit(ApiResponse.Error("No internet connection"))
                        }

                        else -> emit(ApiResponse.Error(e.message.toString()))
                    }
                }.flowOn(Dispatchers.IO)
                .collect { emit(it) }
        }.flowOn(Dispatchers.IO)

    suspend fun getGameDetails(gameId: String): Flow<ApiResponse<GameDetailsResponse>> = flow {
        try {
            val response = mainApiService.getGameDetails(gameId)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val message = when (e.code()) {
                        401 -> "Unauthorized"
                        403 -> "Forbidden"
                        404 -> "Not Found"
                        else -> e.getErrorMessage().toString()
                    }
                    emit(ApiResponse.Error(message))
                }

                is UnknownHostException -> {
                    emit(ApiResponse.Error("No internet connection"))
                }

                else -> emit(ApiResponse.Error(e.message.toString()))
            }
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getSearchDeals(title: String): Flow<ApiResponse<List<DealsResponseItem>>> = flow {
        try {
            val response = mainApiService.getSearchDeals(title)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val message = when (e.code()) {
                        401 -> "Unauthorized"
                        403 -> "Forbidden"
                        404 -> "Not Found"
                        else -> e.getErrorMessage().toString()
                    }
                    emit(ApiResponse.Error(message))
                }

                is UnknownHostException -> {
                    emit(ApiResponse.Error("No internet connection"))
                }

                else -> emit(ApiResponse.Error(e.message.toString()))
            }
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getSortListOfDeals(sortBy: String): Flow<ApiResponse<List<DealsResponseItem>>> = flow {
        try {
            val response = mainApiService.getSortListOfDeals(sortBy)
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val message = when (e.code()) {
                        401 -> "Unauthorized"
                        403 -> "Forbidden"
                        404 -> "Not Found"
                        else -> e.getErrorMessage().toString()
                    }
                    emit(ApiResponse.Error(message))
                }

                is UnknownHostException -> {
                    emit(ApiResponse.Error("No internet connection"))
                }

                else -> emit(ApiResponse.Error(e.message.toString()))
            }
        }
    }.flowOn(Dispatchers.IO)
}