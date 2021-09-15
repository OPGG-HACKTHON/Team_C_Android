package android.milestone.network.response.history


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("nickname")
    val nickname: String = ""
)