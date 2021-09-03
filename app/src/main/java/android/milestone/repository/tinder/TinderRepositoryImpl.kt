package android.milestone.repository.tinder

import android.milestone.network.request.CreateReportRequest
import android.milestone.network.request.CreateTinderRequest
import android.milestone.network.request.UpdateLikeRequest
import android.milestone.network.response.RootResponse
import android.milestone.network.response.tinder.TinderResponse
import android.milestone.network.source.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class TinderRepositoryImpl
@Inject constructor(private val remoteDataSource: RemoteDataSource) : TinderRepository {
    override fun createTinder(createTinderRequest: CreateTinderRequest): Flow<Response<RootResponse>> =
        flow {
            emit(remoteDataSource.createTinder(createTinderRequest))
        }.flowOn(Dispatchers.IO)

    override fun getTinder(count: Int, filter: String): Flow<Response<TinderResponse>> = flow {
        emit(remoteDataSource.getTinder(count, filter))
    }.flowOn(Dispatchers.IO)

    override fun createReport(createReportRequest: CreateReportRequest): Flow<Response<RootResponse>> =
        flow {
            emit(remoteDataSource.createReport(createReportRequest))
        }.flowOn(Dispatchers.IO)

    override fun updateLike(updateLikeRequest: UpdateLikeRequest): Flow<Response<RootResponse>> =
        flow {
            emit(remoteDataSource.updateLike(updateLikeRequest))
        }.flowOn(Dispatchers.IO)
}