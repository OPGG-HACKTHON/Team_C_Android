package android.milestone.repository.user

import android.milestone.network.request.UpdateNicknameRequest
import android.milestone.network.request.UpdatePreferenceRequest
import android.milestone.network.request.UpdateTeamIdRequest
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.MyPageInfo
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UserRepository {

    fun getUserData(): Flow<MyPageInfo?>

    fun updateNickname(nicknameRequest: UpdateNicknameRequest): Flow<Response<RootResponse>>

    fun updateTeamId(updateTeamIdRequest: UpdateTeamIdRequest): Flow<Response<RootResponse>>

    fun updatePreference(updatePreferenceRequest: UpdatePreferenceRequest): Flow<Response<RootResponse>>
}