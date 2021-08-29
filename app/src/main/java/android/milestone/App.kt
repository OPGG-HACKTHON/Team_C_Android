package android.milestone

import android.app.Application
import android.content.Context
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
        KakaoSdk.init(this, BuildConfig.kakao_app_key)

    }

    companion object {
        lateinit var instance: App
            private set

        fun context(): Context {
            return instance.applicationContext
        }
    }
}