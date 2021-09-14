package android.milestone.network.source

import android.milestone.network.request.*
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.LoginResponse
import android.milestone.network.response.auth.TeamInfoResponse
import android.milestone.network.response.home.CurrentGameResponse
import android.milestone.network.response.home.TinderResponse
import android.milestone.network.response.home.pog_list.PogListResponse
import android.milestone.network.response.match_detail.PlayerOfGameResponse
import android.milestone.network.response.tinder.TopTinderResponse
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

    suspend fun getCurrentGame(): CurrentGameResponse

    suspend fun getPogList(): Response<PogListResponse>

    suspend fun postPogVote(pogVoteRequestList: List<PogVoteRequest>): Response<RootResponse>

    suspend fun getPogOfGame(gameId: Int?): Response<PlayerOfGameResponse>
}