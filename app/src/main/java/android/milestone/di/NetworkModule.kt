package android.milestone.di

import android.milestone.BuildConfig
import android.milestone.Naming.ACCESS_TOKEN
import android.milestone.Naming.REFRESH_TOKEN
import android.milestone.network.Api
import android.milestone.util.PrefUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    private const val TIME_OUT_SEC = 5L
    private const val BASE_URL = "http://3.37.194.249"

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun getLoggerInterceptor() = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // TODO: 2021-08-11 나중에 로그인 토큰 넣어야 할듯??
    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("accesstoken", PrefUtil.getStringValue(ACCESS_TOKEN, ""))
                    .addHeader("refreshtoken", PrefUtil.getStringValue(REFRESH_TOKEN, ""))
                    .build()
                val response = it.proceed(request)
                response
            }
            .addNetworkInterceptor(interceptor)
            .connectTimeout(TIME_OUT_SEC, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
}
