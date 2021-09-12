package android.milestone.network.response.home.pog_list


import com.google.gson.annotations.SerializedName

data class PogListData(
    @SerializedName("aTeam")
    val aTeam: PogListTeam,
    @SerializedName("bTeam")
    val bTeam: PogListTeam
)