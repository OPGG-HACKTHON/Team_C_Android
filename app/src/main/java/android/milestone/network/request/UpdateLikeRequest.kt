package android.milestone.network.request

data class UpdateLikeRequest(
    val tinderId: Int,
    val like: Int,
    val dislike: Int,
    val superlike: Int,
    val pass: Int
)