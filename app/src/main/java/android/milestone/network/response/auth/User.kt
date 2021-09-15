package android.milestone.network.response.auth

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("uid")
    val uid: Int,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("teamId")
    val teamId: Int,
    @SerializedName("reportedCount")
    val reportedCount: Int,
    @SerializedName("refreshedAt")
    val refreshedAt: String,
    @SerializedName("teamUpdatedAt")
    val teamUpdatedAt: String,
)