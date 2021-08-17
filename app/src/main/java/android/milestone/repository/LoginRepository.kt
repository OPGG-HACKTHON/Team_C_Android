package android.milestone.repository

import android.milestone.network.response.TeamInfoResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun getTeamInfo(): Flow<TeamInfoResponse>

}