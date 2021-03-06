package android.milestone.network.response.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)