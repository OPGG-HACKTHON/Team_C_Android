package android.milestone.di

import android.milestone.network.source.RemoteDateSource
import android.milestone.network.source.RemoteDateSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class SourceModule {

    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(impl: RemoteDateSourceImpl): RemoteDateSource

}