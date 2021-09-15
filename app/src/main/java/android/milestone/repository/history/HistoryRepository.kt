package android.milestone.repository.history

import android.milestone.network.response.history.BestHistoryResponse
import android.milestone.network.response.history.LatestHistoryResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface HistoryRepository {

    fun loadLatestHistory(): Flow<Response<LatestHistoryResponse>>

    fun loadBestHistory(): Flow<Response<BestHistoryResponse>>
}