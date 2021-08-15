package android.milestone.network.source

import android.milestone.network.response.TeamInfoResponse

interface RemoteDateSource {

    suspend fun getTeamInfo() : TeamInfoResponse

}