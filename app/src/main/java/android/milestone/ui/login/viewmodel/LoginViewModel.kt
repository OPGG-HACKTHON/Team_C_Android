package android.milestone.ui.login.viewmodel

import android.milestone.base.BaseViewModel
import android.milestone.network.request.LoginRequest
import android.milestone.network.request.SignUpRequest
import android.milestone.network.response.LoginResponse
import android.milestone.network.response.SignUpResponse
import android.milestone.network.response.TeamInfoResponse
import android.milestone.repository.LoginRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(private val loginRepository: LoginRepository) : BaseViewModel() {

    val teamInfo: LiveData<TeamInfoResponse> =
        loginRepository.getTeamInfo().asLiveData(coroutineExceptionHandler)

    private val kakaoId = MutableStateFlow(0)

    private val teamId = MutableStateFlow(0)

    private val _accessToken = MutableStateFlow("")
    val accessToken: StateFlow<String> get() = _accessToken

    private val _refreshToken = MutableStateFlow("")
    val refreshToken: StateFlow<String> get() = _refreshToken

    fun postLogin(loginRequest: LoginRequest) = liveData(coroutineExceptionHandler) {
        loginRepository.postLogin(loginRequest)
            .collect { response ->
                val header = response.headers()
                val body = response.body()
                body?.let { loginRespone ->
                    if (loginRespone.success) {
                        _accessToken.value = header["accesstoken"].toString()
                        _refreshToken.value = header["refreshtoken"].toString()
                    }
                    emit(loginRespone.status)
                }
            }
    }

            }
    }

    fun setTeamId(id: Int) {
        teamId.value = id
    }

    fun setKakaoId(id: Int) {
        kakaoId.value = id
    }

}