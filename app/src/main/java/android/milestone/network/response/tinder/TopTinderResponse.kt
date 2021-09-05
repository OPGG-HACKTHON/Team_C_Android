package android.milestone.network.response.tinder


import com.google.gson.annotations.SerializedName

data class TopTinderResponse(
    @SerializedName("data")
    val data: List<TopTinder> = listOf(),
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("success")
    val success: Boolean = false
)