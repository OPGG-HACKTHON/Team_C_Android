package android.milestone.network.response.match_detail


import com.google.gson.annotations.SerializedName

data class PlayerOfGameResponse(
    @SerializedName("data")
    val data: TeamsOfGameInfo = TeamsOfGameInfo(),
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("success")
    val success: Boolean = false
)