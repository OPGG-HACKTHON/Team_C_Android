package android.milestone.network.request

data class CreateReportRequest(
    private val tinderId: Int,
    private val reportMsg: String
)