package android.milestone.network.response.history


import com.google.gson.annotations.SerializedName

data class LatestHistoryResponse(
    @SerializedName("data")
    val data: List<History> = listOf(),
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("success")
    val success: Boolean = false
)