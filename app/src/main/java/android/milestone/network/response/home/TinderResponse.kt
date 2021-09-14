package android.milestone.network.response.home

import android.milestone.network.model.home.TinderModel
import com.google.gson.annotations.SerializedName

data class TinderResponse(
    @SerializedName("data")
    val data: List<TinderModel>,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)