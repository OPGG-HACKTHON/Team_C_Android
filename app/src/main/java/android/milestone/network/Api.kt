package android.milestone.network

import android.milestone.network.response.KakaoAuthenticateResponse
import retrofit2.http.GET

interface Api {

    /*
        보류
    */
    @GET("/auth/kakao")
    suspend fun getKakaoAuthenticate() : KakaoAuthenticateResponse
}