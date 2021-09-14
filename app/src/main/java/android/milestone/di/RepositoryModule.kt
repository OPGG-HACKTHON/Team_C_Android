package android.milestone.di

import android.milestone.repository.history.HistoryRepository
import android.milestone.repository.history.HistoryRepositoryImpl
import android.milestone.repository.home.HomeRepository
import android.milestone.repository.home.HomeRepositoryImpl
import android.milestone.repository.league.LeagueRepository
import android.milestone.repository.league.LeagueRepositoryImpl
import android.milestone.repository.login.LoginRepository
import android.milestone.repository.login.LoginRepositoryImpl
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

    @Singleton
    @Binds
    abstract fun bindHistoryRepository(impl: HistoryRepositoryImpl): HistoryRepository

    @Singleton
    @Binds
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository
}