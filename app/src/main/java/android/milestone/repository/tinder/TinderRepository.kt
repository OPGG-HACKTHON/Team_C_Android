package android.milestone.repository.tinder

import android.milestone.network.request.*
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.LoginResponse
import android.milestone.network.response.auth.TeamInfoResponse
import android.milestone.network.response.tinder.TinderResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface TinderRepository {

    fun createTinder(createTinderRequest: CreateTinderRequest): Flow<Response<RootResponse>>

    fun getTinder(count: Int, filter: String): Flow<Response<TinderResponse>>

    fun createReport(createReportRequest: CreateReportRequest): Flow<Response<RootResponse>>

    fun updateLike(updateLikeRequest: UpdateLikeRequest): Flow<Response<RootResponse>>
}