package android.milestone.network.response.ranking


import com.google.gson.annotations.SerializedName

data class PlayerRankingResponse(
    @SerializedName("data")
    val players: List<Player> = listOf(),
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("success")
    val success: Boolean = false
)