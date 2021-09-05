package android.milestone.ui.schedule

import android.milestone.base.BaseViewModel
import android.milestone.network.response.match_detail.PogPlayer
import android.milestone.network.response.match_detail.PogTeam
import android.milestone.network.response.match_detail.TeamsOfGameInfo
import android.milestone.network.response.tinder.TopTinder
import android.milestone.repository.LeagueRepository
import android.milestone.repository.tinder.TinderRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchDetailViewModel @Inject constructor(
    private val leagueRepository: LeagueRepository,
    private val tinderRepository: TinderRepository
) : BaseViewModel() {

    private val _topTinder: MutableLiveData<List<TopTinder>?> = MutableLiveData()
    val topTinder: LiveData<List<TopTinder>?> = _topTinder

    private val _players: MutableLiveData<TeamsOfGameInfo?> = MutableLiveData()
    val players: LiveData<TeamsOfGameInfo?> = _players

    private val _isFirstTeamSelected: MutableLiveData<Boolean> = MutableLiveData(true)
    val isFirstTeamSelected: LiveData<Boolean> = _isFirstTeamSelected

    val firstTeam: LiveData<PogTeam?> = players.map { it?.aTeam }
    val secondTeam: LiveData<PogTeam?> = players.map { it?.bTeam }
    val selectedTeamPlayers: LiveData<List<PogPlayer>?> = players.map {
        if (isFirstTeamSelected.value == true) it?.aTeam?.player else it?.bTeam?.player
    }
    val pogCount: LiveData<Int> = selectedTeamPlayers.map {
        it?.sumOf { player -> player.count } ?: 0
    }

    fun updateMatchDetail(gameId: Int) {
        loadTopTinder(gameId)
        loadPog(gameId)
    }

    fun changeSelectedTeam(isFirstTeam: Boolean) {
        _isFirstTeamSelected.value = isFirstTeam
    }

    private fun loadTopTinder(gameId: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val response = tinderRepository.getTopTinder(gameId)
            response.collect {
                _topTinder.value = if (it.isSuccessful) it.body()?.data else null
            }
        }
    }

    fun loadPog(gameId: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val response = leagueRepository.getPlayerOfGame(gameId)
            response.collect {
                _players.value = if (it.isSuccessful) it.body()?.data else null
            }
        }
    }
}