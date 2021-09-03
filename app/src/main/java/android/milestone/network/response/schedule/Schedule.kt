package android.milestone.network.response.schedule


import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("aTeamIcon")
    val aTeamIcon: String = "",
    @SerializedName("aTeamName")
    val aTeamName: String = "",
    @SerializedName("aTeamScore")
    val aTeamScore: Int = 0,
    @SerializedName("bTeamIcon")
    val bTeamIcon: String = "",
    @SerializedName("bTeamName")
    val bTeamName: String = "",
    @SerializedName("bTeamScore")
    val bTeamScore: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("startTime")
    val startTime: String = "",
    @SerializedName("status")
    val status: Int = 0
)