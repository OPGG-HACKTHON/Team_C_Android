package android.milestone.repository.home

import android.milestone.network.request.CreateReportRequest
import android.milestone.network.request.CreateTinderRequest
import android.milestone.network.request.UpdateLikeRequest
import android.milestone.network.response.RootResponse
import android.milestone.network.response.home.CurrentGameResponse
import android.milestone.network.response.home.TinderResponse
import android.milestone.network.response.match_detail.PlayerOfGameResponse
import android.milestone.network.response.tinder.TopTinderResponse
import android.milestone.network.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class HomeRepositoryImpl
@Inject constructor(private val remoteDataSource: RemoteDataSource) : HomeRepository {
    override fun createTinder(createTinderRequest: CreateTinderRequest): Flow<Response<RootResponse>> =
        flow {
            emit(remoteDataSource.createTinder(createTinderRequest))
        }

    override fun getTinder(count: Int, filter: String): Flow<Response<TinderResponse>> = flow {
        emit(remoteDataSource.getTinder(count, filter))
    }

    override fun createReport(createReportRequest: CreateReportRequest): Flow<Response<RootResponse>> =
        flow {
            emit(remoteDataSource.createReport(createReportRequest))
        }

    override fun updateLike(updateLikeRequest: UpdateLikeRequest): Flow<Response<RootResponse>> =
        flow {
            emit(remoteDataSource.updateLike(updateLikeRequest))
        }

    override fun getCurrentGame(): Flow<CurrentGameResponse> = flow {
        emit(remoteDataSource.getCurrentGame())
    }

    override fun getTopTinder(gameId: Int): Flow<Response<TopTinderResponse>> = flow {
        emit(remoteDataSource.getTopTinder(gameId))
    }

    override fun getPogOfGame(gameId: Int?): Flow<Response<PlayerOfGameResponse>> = flow {
        emit(remoteDataSource.getPogOfGame(gameId))
    }
}