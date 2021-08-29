package android.milestone.network.response.auth

import android.milestone.network.model.auth.TeamInfoModel
import com.google.gson.annotations.SerializedName

data class TeamInfoResponse(
    @SerializedName("data")
    val data: List<TeamInfoModel>,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)