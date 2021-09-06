package android.milestone.repository.league

import android.milestone.network.Api
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LeagueRepositoryImpl @Inject constructor(private val service: Api) : LeagueRepository {

    override fun getTeamRanking() = flow {
        emit(service.getTeamRank())
    }

    override fun getPlayerRanking() = flow {
        emit(service.getPlayerRank())
    }

    override fun getPlayerOfGame(gameId: Int) = flow {
        emit(service.getPogOfGame(gameId))
    }

    override fun loadSchedule(month: Int) = flow {
        emit(service.loadSchedule(month))
    }
}