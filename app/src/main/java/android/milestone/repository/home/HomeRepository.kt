package android.milestone.repository.home

import android.milestone.network.request.CreateReportRequest
import android.milestone.network.request.CreateTinderRequest
import android.milestone.network.request.UpdateLikeRequest
import android.milestone.network.response.RootResponse
import android.milestone.network.response.home.CurrentGameResponse
import android.milestone.network.response.home.TinderResponse
import android.milestone.network.response.tinder.TopTinderResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface HomeRepository {

    fun createTinder(createTinderRequest: CreateTinderRequest): Flow<Response<RootResponse>>

    fun getTinder(count: Int, filter: String): Flow<Response<TinderResponse>>

    fun createReport(createReportRequest: CreateReportRequest): Flow<Response<RootResponse>>

    fun updateLike(updateLikeRequest: UpdateLikeRequest): Flow<Response<RootResponse>>

    fun getCurrentGame(): Flow<CurrentGameResponse>

    fun getTopTinder(gameId: Int): Flow<Response<TopTinderResponse>>
}