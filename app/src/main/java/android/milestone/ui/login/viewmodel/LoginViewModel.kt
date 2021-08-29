package android.milestone.ui.login.viewmodel

import android.milestone.base.BaseViewModel
import android.milestone.network.request.LoginRequest
import android.milestone.network.request.SignUpRequest
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.LoginResponse
import android.milestone.network.response.auth.TeamInfoResponse
import android.milestone.repository.LoginRepository
import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(private val loginRepository: LoginRepository) : BaseViewModel() {

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> get() = _loginResponse

    private val _signUpResponse = MutableLiveData<RootResponse>()
    val singUpResponse: LiveData<RootResponse> get() = _signUpResponse

    val teamInfo: LiveData<TeamInfoResponse> =
        loginRepository.getTeamInfo().asLiveData(coroutineExceptionHandler)

    private val kakaoId = MutableStateFlow(0)

    private val teamId = MutableStateFlow(0)

    private val _accessToken = MutableStateFlow("")
    val accessToken: StateFlow<String> get() = _accessToken

    private val _refreshToken = MutableStateFlow("")
    val refreshToken: StateFlow<String> get() = _refreshToken

    fun postLogin(loginRequest: LoginRequest) {
        viewModelScope.launch(coroutineExceptionHandler) {
            loginRepository.postLogin(loginRequest)
                .collect { response ->
                    val header = response.headers()
                    val body = response.body()
                    body?.let { loginRespone ->
                        if (loginRespone.success) {
                            _accessToken.value = header["accesstoken"].toString()
                            _refreshToken.value = header["refreshtoken"].toString()
                        }
                        _loginResponse.value = loginRespone
                    }

                }
        }
    }

    fun postSignUp(nickname: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val signUpRequest = SignUpRequest(
                id = kakaoId.value,
                nickname = nickname,
                teamId = if (teamId.value != 0) teamId.value else null,
                provider = "kakao"
            )
            loginRepository.postSignUp(signUpRequest)
                .collect { response ->
                    val header = response.headers()
                    val body = response.body()
                    body?.let { signUpResponse ->
                        if (signUpResponse.success) {
                            _accessToken.value = header["accesstoken"].toString()
                            _refreshToken.value = header["refreshtoken"].toString()
                        }
                        _signUpResponse.value = signUpResponse
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