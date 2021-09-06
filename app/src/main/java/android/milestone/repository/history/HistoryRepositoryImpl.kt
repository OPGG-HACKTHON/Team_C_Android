package android.milestone.repository.history

import android.milestone.network.Api
import android.milestone.network.response.history.BestHistoryResponse
import android.milestone.network.response.history.LatestHistoryResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val serviceApi: Api
) : HistoryRepository {

    override fun loadLatestHistory(): Flow<Response<LatestHistoryResponse>> = flow {
        emit(serviceApi.loadLatestHistory())
    }

    override fun loadBestHistory(): Flow<Response<BestHistoryResponse>> = flow {
        emit(serviceApi.loadBestHistory())
    }
}