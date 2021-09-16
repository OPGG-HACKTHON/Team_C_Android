package android.milestone.network.response.user


import com.google.gson.annotations.SerializedName

data class TinderCount(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("tinders")
    val tinders: Int = 0
)