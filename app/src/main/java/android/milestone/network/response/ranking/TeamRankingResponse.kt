package android.milestone.network.response.ranking


import com.google.gson.annotations.SerializedName

data class TeamRankingResponse(
    @SerializedName("data")
    val teamRanking: List<TeamRanking> = listOf(),
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("success")
    val success: Boolean = false
)