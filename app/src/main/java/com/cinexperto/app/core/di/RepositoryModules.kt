package com.cinexperto.app.core.di

import com.cinexperto.app.features.auth.data.repositories.AuthRepositoryImpl
import com.cinexperto.app.features.auth.domain.repositories.AuthRepository
import com.cinexperto.app.features.trivia.data.repositories.TriviaRepositoryImpl
import com.cinexperto.app.features.trivia.domain.repositories.TriviaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModules {

    @Binds
    abstract fun provideAuthRepository(repository: AuthRepositoryImpl):AuthRepository

    @Binds
    abstract fun provideTriviaRepository(repository: TriviaRepositoryImpl):TriviaRepository

}