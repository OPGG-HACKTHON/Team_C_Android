package android.milestone.ui.schedule

import android.milestone.base.BaseViewModel
import android.milestone.network.response.schedule.Schedule
import android.milestone.repository.LeagueRepository
import android.milestone.ui.schedule.ui_model.ScheduleUiModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val repository: LeagueRepository) : BaseViewModel() {

    private val _searchRange: MutableLiveData<LocalDateTime> = MutableLiveData(LocalDateTime.now())
    val searchRange: LiveData<LocalDateTime> = _searchRange

    private val _scheduleData: MutableLiveData<List<Schedule>?> = MutableLiveData()
    val scheduleData: LiveData<List<ScheduleUiModel>?> = _scheduleData.map {
        it?.map { schedule -> ScheduleUiModel(schedule) }
    }

    fun changeSearchRange(interval: Int = 1) {
        _searchRange.value = _searchRange.value?.plusMonths(interval.toLong())
        updateData()
    }

    fun updateData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val response = repository.loadSchedule(_searchRange.value?.monthValue ?: 1)
            response.collectLatest {
                if (it.isSuccessful) {
                    _scheduleData.value = it.body()?.data
                } else {
                    _scheduleData.value = emptyList()
                }
            }
        }
    }
}