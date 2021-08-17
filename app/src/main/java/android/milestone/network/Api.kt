package android.milestone.network

import android.milestone.network.response.TeamInfoResponse
import retrofit2.http.GET

interface Api {

    @GET("/info/teamInfo")
    suspend fun getTeamInfo(): TeamInfoResponse
}