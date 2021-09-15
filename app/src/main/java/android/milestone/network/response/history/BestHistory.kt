package android.milestone.network.response.history


import com.google.gson.annotations.SerializedName

data class BestHistory(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("rank")
    val rank: Int = 0,
    @SerializedName("tinder")
    val tinder: History = History(),
    @SerializedName("tinderId")
    val tinderId: Int = 0
)