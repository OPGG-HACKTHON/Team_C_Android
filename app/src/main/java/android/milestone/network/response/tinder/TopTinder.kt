package android.milestone.network.response.tinder


import com.google.gson.annotations.SerializedName

data class TopTinder(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("rank")
    val rank: Int = 0,
    @SerializedName("tinder")
    val tinder: Tinder = Tinder(),
    @SerializedName("tinderId")
    val tinderId: Int = 0
)