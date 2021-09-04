package android.milestone.ui.home.viewmodel

import android.milestone.base.BaseViewModel
import android.milestone.network.request.CreateReportRequest
import android.milestone.network.request.UpdateLikeRequest
import android.milestone.network.response.RootResponse
import android.milestone.network.response.home.TinderResponse
import android.milestone.repository.home.HomeRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(private val homeRepository: HomeRepository) : BaseViewModel() {

    private val _tinderResponse = MutableLiveData<TinderResponse>()
    val tinderResponse: LiveData<TinderResponse> get() = _tinderResponse

    private val _rootResponse = MutableLiveData<RootResponse>()
    val rootResponse: LiveData<RootResponse> get() = _rootResponse

    private val _currentTinderId = MutableLiveData<Int>()
    val currentTinderId: LiveData<Int> get() = _currentTinderId

    private val _reportMessage = MutableLiveData<String>()
    val reportMessage: LiveData<String> get() = _reportMessage

    val currentGameResponse = homeRepository.getCurrentGame().asLiveData(coroutineExceptionHandler)

    fun getTinder(count: Int = 10, filter: String = "") {
        viewModelScope.launch(coroutineExceptionHandler) {
            homeRepository.getTinder(count, filter)
                .collect {
                    it.body()?.let { tinderResponse ->
                        _tinderResponse.value = tinderResponse
                    }
                }
        }
    }

    fun updateLike(updateLikeRequest: UpdateLikeRequest) {
        viewModelScope.launch(coroutineExceptionHandler) {
            homeRepository.updateLike(updateLikeRequest)
                .collect {
                    it.body()?.let {
                        // TODO: 2021-08-30 에러 및 성공 처리
                    }
                }
        }
    }

    fun createReport(createReportRequest: CreateReportRequest) {
        viewModelScope.launch(coroutineExceptionHandler) {
            homeRepository.createReport(createReportRequest)
                .collect {
                    it.body()?.let { rootResponse ->
                        // TODO: 2021-08-31 에러 및 성공 처리
                        _rootResponse.value = rootResponse
                    }
                }
        }
    }

    fun setCurrentTinderId(tinderId: Int) {
        _currentTinderId.value = tinderId
    }

    fun setReportMessage(msg: String) {
        _reportMessage.value = msg
    }
}