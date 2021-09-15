package android.milestone.network.response.user


import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image")
    val image: String = "",
    @SerializedName("key")
    val key: Int = 0,
    @SerializedName("nickname")
    val nickname: String = "",
    @SerializedName("point")
    val point: Int = 0,
    @SerializedName("role")
    val role: String = ""
)