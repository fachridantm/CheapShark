package com.ewide.test.fachridan.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ewide.test.fachridan.core.domain.usecase.DealsUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val dealsUseCase: DealsUseCase) : ViewModel() {
    fun getFavoriteGames() = dealsUseCase.getFavoriteGames().asLiveData()
}