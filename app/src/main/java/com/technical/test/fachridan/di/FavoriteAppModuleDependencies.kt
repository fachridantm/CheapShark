package com.technical.test.fachridan.di

import com.technical.test.fachridan.core.domain.usecase.DealsUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun dealsUseCase(): DealsUseCase
}