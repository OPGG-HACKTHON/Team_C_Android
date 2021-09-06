package android.milestone.network.model.home

data class CurrentGameModel(
    val aTeam: TeamModel,
    val aTeamScore: Int,
    val bTeam: TeamModel,
    val bTeamScore: Int,
    val id: Int,
    val startTime: String,
    val status: Int
)