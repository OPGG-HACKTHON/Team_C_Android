package android.milestone.ui.setting

import android.milestone.base.BaseViewModel
import android.milestone.isValidName
import android.milestone.network.model.auth.TeamInfoModel
import android.milestone.network.request.UpdateNicknameRequest
import android.milestone.network.response.user.Profile
import android.milestone.network.response.user.TinderCount
import android.milestone.repository.login.LoginRepository
import android.milestone.repository.user.UserRepository
import androidx.lifecycle.*
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

    private val _userData: MutableLiveData<Profile?> = MutableLiveData()
    val userData: LiveData<Profile?> = _userData

    private val _countData: MutableLiveData<TinderCount?> = MutableLiveData()
    val countData: LiveData<TinderCount?> = _countData

    val teamList: LiveData<List<TeamInfoModel>> = loginRepository.getTeamInfo().asLiveData().map { it.data }

    fun updateUserData() {
        viewModelScope.launch(coroutineExceptionHandler + Dispatchers.IO) {
            val profile = repository.getProfile()
            _userData.postValue(profile.body()?.profile)
            _countData.postValue(countData.value)
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