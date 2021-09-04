package android.milestone.network.response.match_detail


import com.google.gson.annotations.SerializedName

data class TeamsOfGameInfo(
    @SerializedName("aTeam")
    val aTeam: PogTeam = PogTeam(),
    @SerializedName("bTeam")
    val bTeam: PogTeam = PogTeam()
)