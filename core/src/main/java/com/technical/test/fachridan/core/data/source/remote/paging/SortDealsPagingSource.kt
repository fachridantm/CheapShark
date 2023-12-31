package com.technical.test.fachridan.core.data.source.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.technical.test.fachridan.core.data.source.remote.network.MainApiService
import com.technical.test.fachridan.core.data.source.remote.response.DealsResponseItem
import com.technical.test.fachridan.core.utils.getErrorMessage
import retrofit2.HttpException
import java.net.UnknownHostException

class SortDealsPagingSource(
    private val mainApiService: MainApiService,
    private val sortBy: String
) : PagingSource<Int, DealsResponseItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DealsResponseItem> {
        val pageNumber = params.key ?: 0
        val pageSize = 20

        return try {
            val response = mainApiService.getSortListOfDeals(sortBy)

            if (response.isEmpty()) {
                Log.e("SortDealsPagingSource", "load: No more data")
                LoadResult.Error(Exception("No more data"))
            } else {
                LoadResult.Page(
                    data = response,
                    prevKey = if (pageNumber == 0) null else pageNumber - pageSize,
                    nextKey = if (response.size < pageSize) null else pageNumber + pageSize,
                )
            }
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val message = when (e.code()) {
                        401 -> "Unauthorized"
                        403 -> "Forbidden"
                        404 -> "Not Found"
                        else -> e.getErrorMessage().toString()
                    }
                    LoadResult.Error(Exception(message))
                }

                is UnknownHostException -> {
                    LoadResult.Error(Exception("No internet connection"))
                }

                else -> {
                    Log.e("SortDealsPagingSource", "load: ${e.message}")
                    LoadResult.Error(Exception("Something when wrong, please try again later"))
                }
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DealsResponseItem>): Int? {
        return state.anchorPosition
    }
}