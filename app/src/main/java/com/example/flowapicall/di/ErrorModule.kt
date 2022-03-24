package com.example.flowapicall.di

import com.example.flowapicall.error_utils.ErrorManager
import com.example.flowapicall.error_utils.ErrorMapper
import com.example.flowapicall.error_utils.ErrorMapperSource
import com.example.flowapicall.error_utils.ErrorUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorUseCase

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperSource
}
