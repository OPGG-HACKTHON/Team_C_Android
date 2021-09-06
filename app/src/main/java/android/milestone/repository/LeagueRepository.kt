package android.milestone.repository

import android.milestone.network.response.match_detail.PlayerOfGameResponse
import android.milestone.network.response.ranking.PlayerRankingResponse
import android.milestone.network.response.ranking.TeamRankingResponse
import android.milestone.network.response.schedule.MonthlyScheduleResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface LeagueRepository {

    fun getTeamRanking(): Flow<Response<TeamRankingResponse>>

    fun getPlayerRanking(): Flow<Response<PlayerRankingResponse>>

    fun getPlayerOfGame(gameId: Int): Flow<Response<PlayerOfGameResponse>>

    fun loadSchedule(month: Int): Flow<Response<MonthlyScheduleResponse>>
}