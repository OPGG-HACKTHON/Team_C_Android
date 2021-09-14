package android.milestone.network.response.home.pog_list


import com.google.gson.annotations.SerializedName

data class PogListTeam(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("player")
    val player: List<PogListPlayer>
)