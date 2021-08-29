package android.milestone.network.source

import android.milestone.network.Api
import android.milestone.network.request.*
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.LoginResponse
import android.milestone.network.response.auth.TeamInfoResponse
import android.milestone.network.response.tinder.TinderResponse
import retrofit2.Response
import javax.inject.Inject


class RemoteDateSourceImpl
@Inject
constructor(private val api: Api) : RemoteDateSource {
    override suspend fun getTeamInfo(): TeamInfoResponse = api.getTeamInfo()

    override suspend fun postLogin(loginRequest: LoginRequest): Response<LoginResponse> =
        api.postLogin(loginRequest)

    override suspend fun postSignUp(signUpRequest: SignUpRequest): Response<RootResponse> =
        api.postSignUp(signUpRequest)

    override suspend fun createTinder(createTinderRequest: CreateTinderRequest): Response<RootResponse> =
        api.createTinder(createTinderRequest)

    override suspend fun getTinder(count: Int, filter: String): Response<TinderResponse> =
        api.getTinder(count, filter)

    override suspend fun createReport(createReportRequest: CreateReportRequest): Response<RootResponse> =
        api.createReport(createReportRequest)

    override suspend fun updateLike(updateLikeRequest: UpdateLikeRequest): Response<RootResponse> =
        api.updateLike(updateLikeRequest)
}