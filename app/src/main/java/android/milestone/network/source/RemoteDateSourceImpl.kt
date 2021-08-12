package android.milestone.network.source

import android.milestone.network.Api
import android.milestone.network.response.KakaoAuthenticateResponse
import javax.inject.Inject


class RemoteDateSourceImpl
@Inject
constructor(private val api: Api) : RemoteDateSource {
}