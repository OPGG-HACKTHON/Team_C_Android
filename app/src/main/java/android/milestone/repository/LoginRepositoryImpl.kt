package android.milestone.repository

import android.milestone.network.request.LoginRequest
import android.milestone.network.request.SignUpRequest
import android.milestone.network.response.LoginResponse
import android.milestone.network.response.SignUpResponse
import android.milestone.network.response.TeamInfoResponse
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

    override fun postSignUp(signUpRequest: SignUpRequest): Flow<Response<SignUpResponse>> = flow {
        emit(remoteDataSource.postSignUp(signUpRequest))
    }
}