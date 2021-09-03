package android.milestone.network.response.ranking


import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("nickname")
    val nickname: String = "",
    @SerializedName("point")
    val point: Int = 0,
    @SerializedName("rank")
    val rank: Int = 0,
    @SerializedName("role")
    val role: String = "",
    @SerializedName("team")
    val team: Team = Team()
) {
    val fullName = "${team.name} $nickname"
}