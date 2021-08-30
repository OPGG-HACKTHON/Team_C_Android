package android.milestone.ui.main.viewmodel

import android.milestone.base.BaseViewModel
import android.milestone.network.request.CreateTinderRequest
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
class MainViewModel
@Inject
constructor(private val tinderRepository: TinderRepository) : BaseViewModel() {

    private val _tinderState = MutableLiveData<Int>()
    val tinderState : LiveData<Int> get() = _tinderState

    fun createTinder(createTinderRequest: CreateTinderRequest) {
        viewModelScope.launch(coroutineExceptionHandler) {
            tinderRepository.createTinder(createTinderRequest)
                .collect {
                    // TODO: 2021-08-31 에러 및 성공 처리
                    _tinderState.value = it.body()?.status
                }
        }
    }
}