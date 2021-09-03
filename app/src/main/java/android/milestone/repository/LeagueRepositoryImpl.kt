package android.milestone.repository

import android.milestone.network.Api
import android.milestone.network.response.ranking.PlayerRankingResponse
import android.milestone.network.response.ranking.TeamRankingResponse
import android.milestone.network.response.schedule.MonthlyScheduleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class LeagueRepositoryImpl @Inject constructor(private val service: Api) : LeagueRepository {

    override fun getTeamRanking(): Flow<Response<TeamRankingResponse>> {
        return flow {
            emit(service.getTeamRank())
        }.flowOn(Dispatchers.IO)
    }

    override fun getPlayerRanking(): Flow<Response<PlayerRankingResponse>> {
        return flow {
            emit(service.getPlayerRank())
        }.flowOn(Dispatchers.IO)
    }

    override fun loadSchedule(month: Int): Flow<Response<MonthlyScheduleResponse>> {
        return flow {
            emit(service.loadSchedule(month))
        }.flowOn(Dispatchers.IO)
    }
}