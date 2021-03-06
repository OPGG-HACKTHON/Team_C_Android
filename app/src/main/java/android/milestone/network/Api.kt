package android.milestone.network

import android.milestone.network.request.*
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.LoginResponse
import android.milestone.network.response.auth.TeamInfoResponse
import android.milestone.network.response.auth.UserDataResponse
import android.milestone.network.response.history.BestHistoryResponse
import android.milestone.network.response.history.LatestHistoryResponse
import android.milestone.network.response.home.CurrentGameResponse
import android.milestone.network.response.home.TinderResponse
import android.milestone.network.response.home.pog_list.PogListResponse
import android.milestone.network.response.match_detail.PlayerOfGameResponse
import android.milestone.network.response.ranking.PlayerRankingResponse
import android.milestone.network.response.ranking.TeamRankingResponse
import android.milestone.network.response.schedule.MonthlyScheduleResponse
import android.milestone.network.response.tinder.TopTinderResponse
import retrofit2.Response
import retrofit2.http.*

interface Api {

    // auth
    @POST("/auth/login")
    suspend fun postLogin(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/auth/signup")
    suspend fun postSignUp(@Body signUpRequest: SignUpRequest): Response<RootResponse>

    @GET("/auth/read")
    suspend fun getUserData(): Response<UserDataResponse>

    // info

    @GET("/info/teamInfo")
    suspend fun getTeamInfo(): TeamInfoResponse

    @GET("/info/teamRank")
    suspend fun getTeamRank(): Response<TeamRankingResponse>

    @GET("/info/pogRank")
    suspend fun getPlayerRank(): Response<PlayerRankingResponse>

    @GET("/info/schedule")
    suspend fun loadSchedule(
        @Query("year") year: Int,
        @Query("month") month: Int
    ): Response<MonthlyScheduleResponse>

    @GET("/info/currentGame")
    suspend fun getCurrentGame(): CurrentGameResponse

    // tinder
    @POST("/tinder/create")
    suspend fun createTinder(@Body createTinderRequest: CreateTinderRequest): Response<RootResponse>

    @GET("/tinder/totalTinder")
    suspend fun totalTinderCount(): Response<RootResponse>

    @GET("/tinder/totalLike")
    suspend fun totalLikeCount(): Response<RootResponse>

    @GET("/tinder")
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

    @GET("/tinder/toptinder")
    suspend fun getTopTinder(
        @Query("gameId") gameId: Int
    ): Response<TopTinderResponse>

    // pog

    @GET("/pog/result")
    suspend fun getPogOfGame(@Query("gameId") gameId: Int?): Response<PlayerOfGameResponse>

    @GET("/pog/list")
    suspend fun getPogList(): Response<PogListResponse>

    @POST("/pog/vote")
    suspend fun postPogVote(@Body pogVoteRequestList: PogVoteListRequest): Response<RootResponse>

    @GET("/tinder/history")
    suspend fun loadLatestHistory(): Response<LatestHistoryResponse>

    @GET("/tinder/hof")
    suspend fun loadBestHistory(): Response<BestHistoryResponse>

    @PUT("/userSetting/nickname")
    suspend fun updateNickname(@Body updateNicknameRequest: UpdateNicknameRequest): Response<RootResponse>

    @PUT("/userSetting/updateTeamId")
    suspend fun updateTeamId(@Body updateTeamIdRequest: UpdateTeamIdRequest): Response<RootResponse>

    @PUT("/userSetting/preference")
    suspend fun updatePreference(@Body updatePreferenceRequest: UpdatePreferenceRequest): Response<RootResponse>
}