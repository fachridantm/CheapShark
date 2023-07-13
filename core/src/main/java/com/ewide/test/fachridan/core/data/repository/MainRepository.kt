package com.ewide.test.fachridan.core.data.repository

import android.util.Log
import androidx.paging.PagingData
import androidx.paging.map
import com.ewide.test.fachridan.core.data.source.Resource
import com.ewide.test.fachridan.core.data.source.remote.RemoteDataSource
import com.ewide.test.fachridan.core.data.source.remote.network.ApiResponse
import com.ewide.test.fachridan.core.domain.model.Deal
import com.ewide.test.fachridan.core.domain.model.GameDetails
import com.ewide.test.fachridan.core.domain.repository.IMainRepository
import com.ewide.test.fachridan.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : IMainRepository {
    override fun getListOfDeals(): Flow<Resource<PagingData<Deal>>> = flow {
        emit(Resource.Loading())
        try {
            when (val response = remoteDataSource.getListOfDeals().first()) {
                is ApiResponse.Success -> {
                    val data = response.data.map { DataMapper.dealsItemToDeal(it) }
                    emit(Resource.Success(data))
                }

                is ApiResponse.Empty -> {
                    emit(Resource.Success(PagingData.empty()))
                    emit(Resource.Error("Data not found"))
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(response.errorMessage))
                }
            }
        } catch (e: Exception) {
            Log.e("MainRepository", "getListOfDeals: ${e.message}")
            emit(Resource.Error("Something went wrong: ${e.message}"))
        }
    }

    override fun getGameDetails(gameId: String): Flow<Resource<GameDetails>> = flow {
        emit(Resource.Loading())
        try {
            when (val response = remoteDataSource.getGameDetails(gameId).first()) {
                is ApiResponse.Success -> {
                    val data = DataMapper.gameDetailsResponseToGameDetails(response.data)
                    emit(Resource.Success(data))
                }

                is ApiResponse.Empty -> {
                    emit(Resource.Error("Data not found"))
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(response.errorMessage))
                }
            }
        } catch (e: Exception) {
            Log.e("MainRepository", "getGameDetails: ${e.message}")
            emit(Resource.Error("Something went wrong: ${e.message}"))
        }
    }

    override fun getSearchDeals(title: String): Flow<Resource<List<Deal>>> = flow {
        emit(Resource.Loading())
        try {
            when (val response = remoteDataSource.getSearchDeals(title).first()) {
                is ApiResponse.Success -> {
                    val data = response.data.map { DataMapper.dealsItemToDeal(it) }
                    emit(Resource.Success(data))
                }

                is ApiResponse.Empty -> {
                    emit(Resource.Error("Data not found"))
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(response.errorMessage))
                }
            }
        } catch (e: Exception) {
            Log.e("MainRepository", "getSearchDeals: ${e.message}")
            emit(Resource.Error("Something went wrong: ${e.message}"))
        }
    }

    override fun getSortListOfDeals(sortBy: String): Flow<Resource<PagingData<Deal>>> = flow {
        emit(Resource.Loading())
        try {
            when (val response = remoteDataSource.getSortListOfDeals(sortBy).first()) {
                is ApiResponse.Success -> {
                    val data = response.data.map { DataMapper.dealsItemToDeal(it) }
                    emit(Resource.Success(data))
                }

                is ApiResponse.Empty -> {
                    emit(Resource.Error("Data not found"))
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(response.errorMessage))
                }
            }
        } catch (e: Exception) {
            Log.e("MainRepository", "getSortListOfDeals: ${e.message}")
            emit(Resource.Error("Something went wrong: ${e.message}"))
        }
    }
}