package com.ewide.test.fachridan.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ewide.test.fachridan.core.domain.usecase.StoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val storeUseCase: StoresUseCase
) : ViewModel() {
    fun getGameDetails(gameId: String) = storeUseCase.getGameDetails(gameId).asLiveData()
}