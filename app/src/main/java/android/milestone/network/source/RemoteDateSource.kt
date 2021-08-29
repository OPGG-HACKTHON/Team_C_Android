package android.milestone.network.source

import android.milestone.network.request.*
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.LoginResponse
import android.milestone.network.response.auth.TeamInfoResponse
import android.milestone.network.response.tinder.TinderResponse
import retrofit2.Response
import retrofit2.http.*

interface RemoteDateSource {

    suspend fun getTeamInfo(): TeamInfoResponse

    suspend fun postLogin(loginRequest: LoginRequest): Response<LoginResponse>

    suspend fun postSignUp(signUpRequest: SignUpRequest): Response<RootResponse>

    suspend fun createTinder(createTinderRequest: CreateTinderRequest): Response<RootResponse>

    suspend fun getTinder(count: Int, filter: String): Response<TinderResponse>

    suspend fun createReport(createReportRequest: CreateReportRequest): Response<RootResponse>

    suspend fun updateLike(updateLikeRequest: UpdateLikeRequest): Response<RootResponse>
}