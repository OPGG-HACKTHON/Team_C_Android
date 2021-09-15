package android.milestone.ui.setting

import android.milestone.base.BaseViewModel
import android.milestone.isValidName
import android.milestone.network.request.UpdateNicknameRequest
import android.milestone.network.response.auth.MyPageInfo
import android.milestone.repository.login.LoginRepository
import android.milestone.repository.user.UserRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val repository: UserRepository,
    private val loginRepository: LoginRepository
) : BaseViewModel() {

    private val _userData: MutableLiveData<MyPageInfo?> = MutableLiveData()
    val userData: LiveData<MyPageInfo?> = _userData

    fun updateUserData() {
        viewModelScope.launch(coroutineExceptionHandler + Dispatchers.IO) {
            repository.getUserData().collect {
                _userData.postValue(it)
            }
        }
    }

    fun updateNickname(newName: String) {
        if (!newName.isValidName().first) {
            return
        }

        viewModelScope.launch(coroutineExceptionHandler) {
            repository.updateNickname(UpdateNicknameRequest(newName)).collect {
                updateUserData()
            }
        }
    }
}