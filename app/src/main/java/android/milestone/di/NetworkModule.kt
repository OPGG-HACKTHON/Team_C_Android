package android.milestone.di

import android.milestone.Naming.ACCESS_TOKEN
import android.milestone.Naming.REFRESH_TOKEN
import android.milestone.PrefUtil
import android.milestone.network.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Named("accessToken")
    @Singleton
    @Provides
    fun provideAccessToken() =
        PrefUtil.getStringValue(ACCESS_TOKEN, "")

    @Named("refreshToken")
    @Singleton
    @Provides
    fun provideRefreshToken() =
        PrefUtil.getStringValue(REFRESH_TOKEN, "")

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @Named("accessToken") accessToken: String,
        @Named("refreshToken") refreshToken: String
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("accesstoken", accessToken)
                    .addHeader("refreshtoken", refreshToken)
                    .build()
                val response = it.proceed(request)
                response
            }
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

    private const val BASE_URL = "http://3.37.194.249"
}
