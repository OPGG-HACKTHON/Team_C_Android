package android.milestone.ui.history

import android.milestone.base.BaseViewModel
import android.milestone.network.response.history.BestHistory
import android.milestone.network.response.history.History
import android.milestone.repository.history.HistoryRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: HistoryRepository) : BaseViewModel() {

    private val _latestHistory: MutableLiveData<List<History>> = MutableLiveData()
    val latestHistory: LiveData<List<History>> = _latestHistory

    private val _bestHistory: MutableLiveData<List<BestHistory>> = MutableLiveData()
    val bestHistory: LiveData<List<BestHistory>> = _bestHistory

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    fun updateData() {
        loadLatestHistory()
        loadBestHistory()
    }

    fun loadLatestHistory() {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.loadLatestHistory().collect {
                if (it.isSuccessful) {
                    _latestHistory.value = it.body()?.data ?: emptyList()
                } else {
                    _errorMsg.value = it.message()
                }
            }
        }
    }

    fun loadBestHistory() {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.loadBestHistory().collect {
                if (it.isSuccessful) {
                    _bestHistory.value = it.body()?.data ?: emptyList()
                } else {
                    _errorMsg.value = it.message()
                }
            }
        }
    }
}