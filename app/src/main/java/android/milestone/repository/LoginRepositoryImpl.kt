package android.milestone.repository

import android.milestone.network.source.RemoteDateSource
import javax.inject.Inject

class LoginRepositoryImpl
@Inject constructor(private val remoteDataSource: RemoteDateSource) : LoginRepository {
}