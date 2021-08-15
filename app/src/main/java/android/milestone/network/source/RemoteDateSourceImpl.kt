package android.milestone.network.source

import android.milestone.network.Api
import android.milestone.network.response.TeamInfoResponse
import javax.inject.Inject


class RemoteDateSourceImpl
@Inject
constructor(private val api: Api) : RemoteDateSource {
    override suspend fun getTeamInfo(): TeamInfoResponse {
        return api.getTeamInfo()
    }
}