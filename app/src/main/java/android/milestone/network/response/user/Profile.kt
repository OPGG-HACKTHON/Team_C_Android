package android.milestone.network.response.user


import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("nickname")
    val nickname: String = "",
    @SerializedName("teamIcon")
    val teamIcon: String = "",
    @SerializedName("teamName")
    val teamName: String = ""
)