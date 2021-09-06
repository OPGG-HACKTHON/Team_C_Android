package android.milestone.repository.tinder

import android.milestone.network.request.CreateReportRequest
import android.milestone.network.request.CreateTinderRequest
import android.milestone.network.request.UpdateLikeRequest
import android.milestone.network.response.RootResponse
import android.milestone.network.response.tinder.TinderResponse
import android.milestone.network.response.tinder.TopTinderResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface TinderRepository {

    fun createTinder(createTinderRequest: CreateTinderRequest): Flow<Response<RootResponse>>

    fun getTinder(count: Int, filter: String): Flow<Response<TinderResponse>>

    fun getTopTinder(gameId: Int): Flow<Response<TopTinderResponse>>

    fun createReport(createReportRequest: CreateReportRequest): Flow<Response<RootResponse>>

    fun updateLike(updateLikeRequest: UpdateLikeRequest): Flow<Response<RootResponse>>
}