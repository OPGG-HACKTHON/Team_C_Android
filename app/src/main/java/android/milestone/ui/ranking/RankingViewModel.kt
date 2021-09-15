package android.milestone.ui.ranking

import android.milestone.base.BaseViewModel
import android.milestone.network.response.ranking.Player
import android.milestone.network.response.ranking.TeamRanking
import android.milestone.repository.league.LeagueRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(private val repository: LeagueRepository) : BaseViewModel() {

    private val _teamRanking: MutableLiveData<List<TeamRanking>?> = MutableLiveData()
    val teamRanking: LiveData<List<TeamRanking>?> = _teamRanking

    private val _playerRanking: MutableLiveData<List<Player>?> = MutableLiveData()
    val playerRanking: LiveData<List<Player>?> = _playerRanking

    fun updateData() {
        updatePlayerData()
        updateTeamData()
    }

    fun updatePlayerData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val response = repository.getPlayerRanking()
            response.collectLatest {
                _playerRanking.value = if (it.isSuccessful) {
                    it.body()?.players
                } else {
                    null
                }
            }
        }
    }

    fun updateTeamData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val response = repository.getTeamRanking()
            response.collectLatest {
                _teamRanking.value = if (it.isSuccessful) {
                    it.body()?.teamRanking
                } else {
                    null
                }
            }
        }
    }

    override fun onError() {
        super.onError()
        _teamRanking.value = null
        _playerRanking.value = null
    }
}