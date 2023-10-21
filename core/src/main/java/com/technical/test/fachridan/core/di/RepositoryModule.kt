package com.technical.test.fachridan.core.di

import com.technical.test.fachridan.core.data.repository.MainRepository
import com.technical.test.fachridan.core.domain.repository.IMainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsMainRepository(mainRepository: MainRepository): IMainRepository
}