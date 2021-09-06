package android.milestone.network.source

import android.milestone.network.request.*
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.LoginResponse
import android.milestone.network.response.auth.TeamInfoResponse
import android.milestone.network.response.tinder.TopTinderResponse
import android.milestone.network.response.home.CurrentGameResponse
import android.milestone.network.response.home.TinderResponse
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getTeamInfo(): TeamInfoResponse

    suspend fun postLogin(loginRequest: LoginRequest): Response<LoginResponse>

    suspend fun postSignUp(signUpRequest: SignUpRequest): Response<RootResponse>

    suspend fun createTinder(createTinderRequest: CreateTinderRequest): Response<RootResponse>

    suspend fun getTinder(count: Int, filter: String): Response<TinderResponse>

    suspend fun getTopTinder(gameId: Int): Response<TopTinderResponse>

    suspend fun createReport(createReportRequest: CreateReportRequest): Response<RootResponse>

    suspend fun updateLike(updateLikeRequest: UpdateLikeRequest): Response<RootResponse>

    suspend fun getCurrentGame() : CurrentGameResponse
}