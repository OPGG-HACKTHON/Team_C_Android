package android.milestone.network.response.tinder


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("nickname")
    val nickname: String = ""
)