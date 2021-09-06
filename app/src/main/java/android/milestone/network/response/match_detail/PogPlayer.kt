package android.milestone.network.response.match_detail


import com.google.gson.annotations.SerializedName

data class PogPlayer(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("gamePlayerId")
    val gamePlayerId: Int = 0,
    @SerializedName("image")
    val image: String = "",
    @SerializedName("nickname")
    val nickname: String = "",
    @SerializedName("rate")
    val rate: Float = 0f,
    @SerializedName("role")
    val role: String = "",
    @SerializedName("rateRank")
    val rateRank: Int = 0
) {
    fun rateString() = "${rate.toInt()}%"
}