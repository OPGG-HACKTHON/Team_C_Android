package android.milestone.network.response.auth

import com.google.gson.annotations.SerializedName

data class UserDataResponse(
    @SerializedName("data")
    val data: User,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)