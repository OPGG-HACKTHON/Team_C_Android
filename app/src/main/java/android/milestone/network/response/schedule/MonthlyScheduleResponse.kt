package android.milestone.network.response.schedule


import com.google.gson.annotations.SerializedName

data class MonthlyScheduleResponse(
    @SerializedName("data")
    val data: List<Schedule> = listOf(),
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("success")
    val success: Boolean = false
)