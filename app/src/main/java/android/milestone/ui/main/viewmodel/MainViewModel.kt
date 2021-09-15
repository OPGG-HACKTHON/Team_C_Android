package android.milestone.ui.main.viewmodel

import android.milestone.base.BaseViewModel
import android.milestone.network.request.CreateTinderRequest
import android.milestone.repository.home.HomeRepository
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val homeRepository: HomeRepository) : BaseViewModel() {

    private val _tinderState = MutableLiveData<Int>()
    val tinderState: LiveData<Int> get() = _tinderState

    private val gameState = MutableLiveData<Int>(-1)

    init {
        initGameState()
    }

    fun createTinder(msg: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            if (gameState.value == -1) {
                initGameState { sendRequest(msg) }
            } else {
                sendRequest(msg)
            }
        }
    }

    private fun sendRequest(msg: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val createTinderRequest = CreateTinderRequest(msg, gameState.value)
            homeRepository.createTinder(createTinderRequest).collect {
                _tinderState.value = it.body()?.status
            }
        }
    }

    private fun initGameState(onSuccess: () -> Unit = {}) {
        viewModelScope.launch(coroutineExceptionHandler) {
            homeRepository.getCurrentGame().collect {
                val currentGameModel = it.data
                gameState.value = currentGameModel?.id
                onSuccess()
            }
        }
    }
}