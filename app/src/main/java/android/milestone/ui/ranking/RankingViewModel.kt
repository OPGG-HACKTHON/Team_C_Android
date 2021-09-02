package android.milestone.ui.ranking

import android.milestone.base.BaseViewModel
import android.milestone.network.response.ranking.Player
import android.milestone.network.response.ranking.TeamRanking
import android.milestone.repository.LeagueRepository
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

    fun updatePlayerData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val response = repository.getPlayerRanking()
            response.collectLatest {
                if (it.isSuccessful) {
                    _playerRanking.value = it.body()?.players
                } else {
                    _playerRanking.value = null
                }
            }
        }
    }

    fun updateTeamData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val response = repository.getTeamRanking()
            response.collectLatest {
                if (it.isSuccessful) {
                    _teamRanking.value = it.body()?.teamRanking
                } else {
                    _teamRanking.value = null
                }
            }
        }
    }

    fun initData() {
        updatePlayerData()
        updateTeamData()
    }
}