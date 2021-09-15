package android.milestone.repository.user

import android.milestone.network.Api
import android.milestone.network.request.UpdateNicknameRequest
import android.milestone.network.request.UpdatePreferenceRequest
import android.milestone.network.request.UpdateTeamIdRequest
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.MyPageInfo
import android.milestone.network.response.auth.TeamInfoResponse
import android.milestone.network.response.auth.UserDataResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: Api) : UserRepository {

    override fun getUserData(): Flow<MyPageInfo?> {
        return flow { emit(api.getUserData()) }
            .combine(flow { emit(api.getTeamInfo()) }) { user, team ->
                combineUserData(user, team)
            }.combine(
                flow {
                    val likeCount = api.totalLikeCount().body()?.data
                    val tinderCount = api.totalTinderCount().body()?.data
                    emit(Pair(likeCount, tinderCount))
                }
            ) { myInfo, count ->
                MyPageInfo(
                    myInfo?.user,
                    myInfo?.team,
                    count.first,
                    count.second,
                )
            }
    }

    private fun combineUserData(user: Response<UserDataResponse>, team: TeamInfoResponse) =
        if (user.isSuccessful) {
            val selectedTeam = team.data.firstOrNull { it.id == user.body()?.data?.teamId }
            MyPageInfo(user.body()?.data, selectedTeam)
        } else {
            null
        }

    override fun updateNickname(nicknameRequest: UpdateNicknameRequest): Flow<Response<RootResponse>> {
        return flow {
            api.updateNickname(nicknameRequest)
        }
    }

    override fun updateTeamId(updateTeamIdRequest: UpdateTeamIdRequest): Flow<Response<RootResponse>> {
        return flow {
            api.updateTeamId(updateTeamIdRequest)
        }
    }

    override fun updatePreference(updatePreferenceRequest: UpdatePreferenceRequest): Flow<Response<RootResponse>> {
        return flow {
            api.updatePreference(updatePreferenceRequest)
        }
    }
}