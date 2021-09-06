package android.milestone.network.response.history


import com.google.gson.annotations.SerializedName

data class History(
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("dislike")
    val dislike: Int = 0,
    @SerializedName("gameId")
    val gameId: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("like")
    val like: Int = 0,
    @SerializedName("message")
    val message: String = "",
    @SerializedName("pass")
    val pass: Int = 0,
    @SerializedName("superlike")
    val superlike: Int = 0,
    @SerializedName("teamId")
    val teamId: Int = 0,
    @SerializedName("updatedAt")
    val updatedAt: String = "",
    @SerializedName("userId")
    val userId: Int = 0
)