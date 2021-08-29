package android.milestone.network.request

data class SignUpRequest(
    val id: Int,
    val nickname: String,
    val teamId: Int?,
    val provider : String
)