package android.milestone.network.response.match_detail


import com.google.gson.annotations.SerializedName

data class PogTeam(
    @SerializedName("icon")
    val icon: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("player")
    val player: List<PogPlayer> = listOf()
)