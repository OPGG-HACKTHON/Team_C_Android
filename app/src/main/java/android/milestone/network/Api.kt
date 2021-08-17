package android.milestone.network

import android.milestone.network.request.LoginRequest
import android.milestone.network.request.SignUpRequest
import android.milestone.network.response.LoginResponse
import android.milestone.network.response.SignUpResponse
import android.milestone.network.response.TeamInfoResponse
import org.checkerframework.framework.qual.PostconditionAnnotation
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("/info/teamInfo")
    suspend fun getTeamInfo(): TeamInfoResponse

    @POST("/auth/login")
    suspend fun postLogin(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/auth/signup")
    suspend fun postSignUp(@Body signUpRequest: SignUpRequest): Response<SignUpResponse>
}