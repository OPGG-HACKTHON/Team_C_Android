package android.milestone.network.response.user


import com.google.gson.annotations.SerializedName

data class UserProfileResponse(
    @SerializedName("data")
    val profile: Profile = Profile(),
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("success")
    val success: Boolean = false
)