package android.milestone.ui.home.viewmodel

import android.milestone.base.BaseViewModel
import android.milestone.repository.login.LoginRepository
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel
@Inject
constructor(loginRepository: LoginRepository) : BaseViewModel() {

    val teamInfoResponse = loginRepository.getTeamInfo().asLiveData(coroutineExceptionHandler)
}