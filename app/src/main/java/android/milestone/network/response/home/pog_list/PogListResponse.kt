package android.milestone.network.response.home.pog_list


import com.google.gson.annotations.SerializedName

data class PogListResponse(
    @SerializedName("data")
    val data: PogListData,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)