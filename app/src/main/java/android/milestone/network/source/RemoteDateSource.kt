package android.milestone.network.source

import android.milestone.network.request.LoginRequest
import android.milestone.network.request.SignUpRequest
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.LoginResponse
import android.milestone.network.response.auth.TeamInfoResponse
import retrofit2.Response

interface RemoteDateSource {

    suspend fun getTeamInfo(): TeamInfoResponse

    suspend fun postLogin(loginRequest: LoginRequest): Response<LoginResponse>

    suspend fun postSignUp(signUpRequest: SignUpRequest): Response<RootResponse>

}