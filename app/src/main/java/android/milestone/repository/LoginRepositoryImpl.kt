package android.milestone.repository

import android.milestone.network.request.LoginRequest
import android.milestone.network.request.SignUpRequest
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.LoginResponse
import android.milestone.network.response.auth.TeamInfoResponse
import android.milestone.network.source.RemoteDateSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl
@Inject constructor(private val remoteDataSource: RemoteDateSource) : LoginRepository {
    override fun getTeamInfo(): Flow<TeamInfoResponse> = flow {
        emit(remoteDataSource.getTeamInfo())
    }.flowOn(Dispatchers.IO)

    override fun postLogin(loginRequest: LoginRequest): Flow<Response<LoginResponse>> = flow {
        emit(remoteDataSource.postLogin(loginRequest))
    }

    override fun postSignUp(signUpRequest: SignUpRequest): Flow<Response<RootResponse>> = flow {
        emit(remoteDataSource.postSignUp(signUpRequest))
    }
}