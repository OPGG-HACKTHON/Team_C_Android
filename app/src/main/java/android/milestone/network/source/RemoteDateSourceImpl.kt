package android.milestone.network.source

import android.milestone.network.Api
import android.milestone.network.request.LoginRequest
import android.milestone.network.request.SignUpRequest
import android.milestone.network.response.LoginResponse
import android.milestone.network.response.SignUpResponse
import android.milestone.network.response.TeamInfoResponse
import retrofit2.Response
import javax.inject.Inject


class RemoteDateSourceImpl
@Inject
constructor(private val api: Api) : RemoteDateSource {
    override suspend fun getTeamInfo(): TeamInfoResponse = api.getTeamInfo()

    override suspend fun postLogin(loginRequest: LoginRequest): Response<LoginResponse> =
        api.postLogin(loginRequest)

    override suspend fun postSignUp(signUpRequest: SignUpRequest): Response<SignUpResponse> =
        api.postSignUp(signUpRequest)
}