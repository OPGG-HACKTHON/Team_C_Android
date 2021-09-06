package android.milestone.network.response.ranking


import com.google.gson.annotations.SerializedName

data class TeamRanking(
    @SerializedName("icon")
    val icon: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("lose")
    val lose: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("rank")
    val rank: Int = 0,
    @SerializedName("rate")
    val rate: Int = 0,
    @SerializedName("win")
    val win: Int = 0
) {
    fun rateString() = "$rate%"
}