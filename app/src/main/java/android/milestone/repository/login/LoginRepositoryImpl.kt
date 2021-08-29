package android.milestone.repository.login

import android.milestone.network.request.*
import android.milestone.network.response.RootResponse
import android.milestone.network.response.auth.LoginResponse
import android.milestone.network.response.auth.TeamInfoResponse
import android.milestone.network.source.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl
@Inject constructor(private val remoteDataSource: RemoteDataSource) : LoginRepository {
    override fun getTeamInfo(): Flow<TeamInfoResponse> = flow {
        emit(remoteDataSource.getTeamInfo())
    }.flowOn(Dispatchers.IO)

    override fun postLogin(loginRequest: LoginRequest): Flow<Response<LoginResponse>> = flow {
        emit(remoteDataSource.postLogin(loginRequest))
    }.flowOn(Dispatchers.IO)

    override fun postSignUp(signUpRequest: SignUpRequest): Flow<Response<RootResponse>> = flow {
        emit(remoteDataSource.postSignUp(signUpRequest))
    }.flowOn(Dispatchers.IO)
}