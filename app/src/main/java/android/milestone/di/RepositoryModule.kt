package android.milestone.di

import android.milestone.repository.LeagueRepository
import android.milestone.repository.LeagueRepositoryImpl
import android.milestone.repository.LoginRepository
import android.milestone.repository.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository

    @Singleton
    @Binds
    abstract fun bindScheduleRepository(impl: LeagueRepositoryImpl): LeagueRepository
}