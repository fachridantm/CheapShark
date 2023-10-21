package com.technical.test.fachridan.di

import com.technical.test.fachridan.core.domain.usecase.DealsInteractor
import com.technical.test.fachridan.core.domain.usecase.DealsUseCase
import com.technical.test.fachridan.core.domain.usecase.StoresInteractor
import com.technical.test.fachridan.core.domain.usecase.StoresUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindsDealsUseCase(dealsInteractor: DealsInteractor): DealsUseCase


    @Binds
    @Singleton
    abstract fun bindsStoreUseCase(storesInteractor: StoresInteractor): StoresUseCase
}