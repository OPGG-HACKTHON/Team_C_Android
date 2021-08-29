package android.milestone.network.request

data class UpdateLikeRequest(
    private val tinderId: Int,
    private val like: Int,
    private val disLike: Int,
    private val superLike: Int,
    private val pass: Int
)