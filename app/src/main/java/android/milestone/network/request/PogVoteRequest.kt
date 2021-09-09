package android.milestone.network.request

data class PogVoteRequest(
    val gamePlayerId: Int,
    var count: Int = 0
)