package android.milestone.network.response


import com.google.gson.annotations.SerializedName

/*
   보류
 */
data class KakaoAuthenticateResponse(
    @SerializedName("data")
    val `data`: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)