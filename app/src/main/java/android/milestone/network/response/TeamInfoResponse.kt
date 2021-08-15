package android.milestone.network.response

import com.google.gson.annotations.SerializedName

data class TeamInfoResponse(
    @SerializedName("data")
    val data: List<TeamInfoModel>,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) {
    data class TeamInfoModel(
        val icon: String,
        val id: Int,
        val name: String
    )
}