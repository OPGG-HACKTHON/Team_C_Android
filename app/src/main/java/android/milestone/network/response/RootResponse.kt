package android.milestone.network.response

import com.google.gson.annotations.SerializedName

data class RootResponse(
    @SerializedName("data")
    val data: String,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)