package android.milestone.network.response.tinder


import com.google.gson.annotations.SerializedName

data class Tinder(
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
    @SerializedName("team")
    val team: Team = Team(),
    @SerializedName("user")
    val user: User = User()
)