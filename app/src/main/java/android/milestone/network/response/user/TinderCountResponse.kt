package android.milestone.network.response.user


import com.google.gson.annotations.SerializedName

data class TinderCountResponse(
    @SerializedName("data")
    val data: TinderCount = TinderCount(),
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("success")
    val success: Boolean = false
)