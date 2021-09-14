package android.milestone.network.source

import android.milestone.network.Api
import android.milestone.network.request.*
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.LoginResponse
import android.milestone.network.response.auth.TeamInfoResponse
import android.milestone.network.response.home.CurrentGameResponse
import android.milestone.network.response.home.TinderResponse
import android.milestone.network.response.match_detail.PlayerOfGameResponse
import android.milestone.network.response.tinder.TopTinderResponse
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSourceImpl
@Inject
constructor(private val api: Api) : RemoteDataSource {
    override suspend fun getTeamInfo(): TeamInfoResponse = api.getTeamInfo()

    override suspend fun postLogin(loginRequest: LoginRequest): Response<LoginResponse> =
        api.postLogin(loginRequest)

    override suspend fun postSignUp(signUpRequest: SignUpRequest): Response<RootResponse> =
        api.postSignUp(signUpRequest)

    override suspend fun createTinder(createTinderRequest: CreateTinderRequest): Response<RootResponse> =
        api.createTinder(createTinderRequest)

    override suspend fun getTinder(count: Int, filter: String): Response<TinderResponse> =
        api.getTinder(count, filter)

    override suspend fun getTopTinder(gameId: Int): Response<TopTinderResponse> =
        api.getTopTinder(gameId)

    override suspend fun createReport(createReportRequest: CreateReportRequest): Response<RootResponse> =
        api.createReport(createReportRequest)

    override suspend fun updateLike(updateLikeRequest: UpdateLikeRequest): Response<RootResponse> =
        api.updateLike(updateLikeRequest)

    override suspend fun getPogOfGame(gameId: Int?): Response<PlayerOfGameResponse> =
        api.getPogOfGame(gameId)

    override suspend fun getCurrentGame(): CurrentGameResponse = api.getCurrentGame()
}