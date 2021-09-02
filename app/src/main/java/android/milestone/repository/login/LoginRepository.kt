package android.milestone.repository.login

import android.milestone.network.request.LoginRequest
import android.milestone.network.request.SignUpRequest
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.LoginResponse
import android.milestone.network.response.auth.TeamInfoResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface LoginRepository {

    fun getTeamInfo(): Flow<TeamInfoResponse>

    fun postLogin(loginRequest: LoginRequest): Flow<Response<LoginResponse>>

    fun postSignUp(signUpRequest: SignUpRequest): Flow<Response<RootResponse>>

}