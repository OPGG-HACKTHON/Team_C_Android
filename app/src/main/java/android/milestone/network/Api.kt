package android.milestone.network

import android.milestone.network.request.*
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.LoginResponse
import android.milestone.network.response.auth.TeamInfoResponse
import android.milestone.network.response.ranking.PlayerRankingResponse
import android.milestone.network.response.ranking.TeamRankingResponse
import android.milestone.network.response.schedule.MonthlyScheduleResponse
import android.milestone.network.response.tinder.TinderResponse
import retrofit2.Response
import retrofit2.http.*

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

    // tinder

    @POST("/tinder/create")
    suspend fun createTinder(@Body createTinderRequest: CreateTinderRequest): Response<RootResponse>

    @GET("/tinder/")
    suspend fun getTinder(
        @Query("count") count: Int,
        @Query("filter") filter: String
    ): Response<TinderResponse>

    @POST("/tinder/report")
    suspend fun createReport(
        @Body createReportRequest: CreateReportRequest
    ): Response<RootResponse>

    @PUT("/tinder/react")
    suspend fun updateLike(
        @Body updateLikeRequest: UpdateLikeRequest
    ): Response<RootResponse>
}