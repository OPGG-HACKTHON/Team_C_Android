package android.milestone.network

import android.milestone.network.request.LoginRequest
import android.milestone.network.request.SignUpRequest
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.LoginResponse
import android.milestone.network.response.auth.TeamInfoResponse
import android.milestone.network.response.ranking.PlayerRankingResponse
import android.milestone.network.response.ranking.TeamRankingResponse
import android.milestone.network.response.schedule.MonthlyScheduleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    // auth

    @GET("/info/teamInfo")
    suspend fun getTeamInfo(): TeamInfoResponse

    @GET("/info/teamRank")
    suspend fun getTeamRank(): Response<TeamRankingResponse>

    @GET("/info/pogRank")
    suspend fun getPlayerRank(): Response<PlayerRankingResponse>

    @POST("/auth/login")
    suspend fun postLogin(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/auth/signup")
    suspend fun postSignUp(@Body signUpRequest: SignUpRequest): Response<RootResponse>

    @GET("/info/schedule")
    suspend fun loadSchedule(@Query("month") month: Int): Response<MonthlyScheduleResponse>
}