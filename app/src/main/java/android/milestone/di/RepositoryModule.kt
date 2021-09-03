package android.milestone.di

import android.milestone.repository.LeagueRepository
import android.milestone.repository.LeagueRepositoryImpl
import android.milestone.repository.login.LoginRepository
import android.milestone.repository.login.LoginRepositoryImpl
import android.milestone.repository.tinder.TinderRepository
import android.milestone.repository.tinder.TinderRepositoryImpl
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
    abstract fun bindTinderRepository(impl: TinderRepositoryImpl): TinderRepository
}