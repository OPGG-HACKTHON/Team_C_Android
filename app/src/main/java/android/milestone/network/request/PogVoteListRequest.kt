package android.milestone.network.request

import com.google.gson.annotations.SerializedName

data class PogVoteListRequest(
    @SerializedName("vote")
    val vote : List<PogVoteRequest>
)