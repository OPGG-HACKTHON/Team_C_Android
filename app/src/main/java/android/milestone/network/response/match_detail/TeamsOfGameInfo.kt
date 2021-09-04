package android.milestone.network.response.match_detail


import com.google.gson.annotations.SerializedName

data class TeamsOfGameInfo(
    @SerializedName("aTeam")
    val aTeam: ATeam = ATeam(),
    @SerializedName("bTeam")
    val bTeam: BTeam = BTeam()
)