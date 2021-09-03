package android.milestone.network.response.ranking


import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("icon")
    val icon: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = ""
)