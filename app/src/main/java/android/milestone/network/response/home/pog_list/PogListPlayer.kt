package android.milestone.network.response.home.pog_list


import com.google.gson.annotations.SerializedName

data class PogListPlayer(
    @SerializedName("gamePlayerId")
    val gamePlayerId: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("role")
    val role: String
)