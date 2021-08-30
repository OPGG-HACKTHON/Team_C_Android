package android.milestone.ui.home.viewmodel

import android.milestone.base.BaseViewModel
import android.milestone.network.request.CreateReportRequest
import android.milestone.network.request.UpdateLikeRequest
import android.milestone.network.response.RootResponse
import android.milestone.network.response.tinder.TinderResponse
import android.milestone.repository.tinder.TinderRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(private val tinderRepository: TinderRepository) : BaseViewModel() {

    private val _tinderResponse = MutableLiveData<TinderResponse>()
    val tinderResponse: LiveData<TinderResponse> get() = _tinderResponse

    private val _rootResponse = MutableLiveData<RootResponse>()
    val rootResponse : LiveData<RootResponse> get() = _rootResponse

    init {
        getTinder()
    }
    fun getTinder(count: Int = 10, filter: String = "") {
        viewModelScope.launch(coroutineExceptionHandler) {
            tinderRepository.getTinder(count, filter)
                .collect {
                    it.body()?.let { tinderResponse ->
                        _tinderResponse.value = tinderResponse
                    }
                }
        }
    }

    fun updateLike(updateLikeRequest: UpdateLikeRequest){
        viewModelScope.launch(coroutineExceptionHandler) {
            tinderRepository.updateLike(updateLikeRequest)
                .collect {
                    it.body()?.let {
                        // TODO: 2021-08-30 에러 및 성공 처리
                    }
                }
        }
    }

    fun createReport(createReportRequest: CreateReportRequest){
        viewModelScope.launch(coroutineExceptionHandler) {
            tinderRepository.createReport(createReportRequest)
                .collect {
                    it.body()?.let { rootResponse ->
                        // TODO: 2021-08-31 에러 및 성공 처리
                        _rootResponse.value = rootResponse
                    }
                }
        }
    }
}