package android.milestone.ui.login

import android.milestone.base.BaseViewModel
import android.milestone.repository.LoginRepository
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(private val loginRepository: LoginRepository) : BaseViewModel() {

}