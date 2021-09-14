package android.milestone.ui.home.viewmodel

import android.milestone.base.BaseViewModel
import android.milestone.network.model.auth.TeamInfoModel
import android.milestone.repository.login.LoginRepository
import android.milestone.util.PrefUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel
@Inject
constructor(loginRepository: LoginRepository) : BaseViewModel() {

    val teamInfoResponse = loginRepository.getTeamInfo().asLiveData(coroutineExceptionHandler)

    private val _unSelectTeamString = MutableLiveData<String>()
    val unSelectTeamString: LiveData<String> get() = _unSelectTeamString

    private val unSelectTeamList =
        PrefUtil.getStringValue(PrefUtil.UNSELECT_TEAM_LIST, "")
            .split(",")
            .toMutableList()


    fun setFilter(teamInfoModel: TeamInfoModel) {
        unSelectTeamList.run {
            val id = teamInfoModel.id.toString()
            if (contains(id)) {
                remove(id)
            } else {
                add(id)
            }
        }
        _unSelectTeamString.value = unSelectTeamList.joinToString(",")
    }
}