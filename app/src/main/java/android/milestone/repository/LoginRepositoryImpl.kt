package android.milestone.repository

import android.milestone.network.response.TeamInfoResponse
import android.milestone.network.source.RemoteDateSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginRepositoryImpl
@Inject constructor(private val remoteDataSource: RemoteDateSource) : LoginRepository {
    override fun getTeamInfo(): Flow<TeamInfoResponse> = flow {
        emit(remoteDataSource.getTeamInfo())
    }.flowOn(Dispatchers.IO)
}