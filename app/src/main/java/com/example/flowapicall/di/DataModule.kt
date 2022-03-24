
package com.example.flowapicall.di

import com.example.flowapicall.repository.Repository
import com.example.flowapicall.repository.RepositorySource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideRepository(dataRepository: Repository): RepositorySource
}
