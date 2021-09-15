package android.milestone.network.response.user


import com.google.gson.annotations.SerializedName

data class UserPreferenceResponse(
    @SerializedName("data")
    val data: List<Player> = listOf(),
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("success")
    val success: Boolean = false
)