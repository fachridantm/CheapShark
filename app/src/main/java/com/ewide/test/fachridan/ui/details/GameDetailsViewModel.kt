package com.ewide.test.fachridan.ui.details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ewide.test.fachridan.R
import com.ewide.test.fachridan.core.domain.model.Deal
import com.ewide.test.fachridan.core.domain.usecase.DealsUseCase
import com.ewide.test.fachridan.core.domain.usecase.StoresUseCase
import com.ewide.test.fachridan.core.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val storeUseCase: StoresUseCase,
    private val dealsUseCase: DealsUseCase
) : ViewModel() {

    private val _toast = MutableLiveData<Event<String>>()
    val toast: LiveData<Event<String>> = _toast

    fun getGameDetails(gameId: String) = storeUseCase.getGameDetails(gameId).asLiveData()
    fun getFavoriteGames() = dealsUseCase.getFavoriteGames().asLiveData()
    fun setFavoriteGames(deal: Deal, isFavorite: Boolean, context: Context) =
        viewModelScope.launch {
            dealsUseCase.setFavoriteGames(deal, isFavorite)
            Event(
                if (isFavorite) {
                    context.getString(R.string.save_favorite, deal.title)
                } else {
                    context.getString(R.string.remove_favorite, deal.title)
                }
            ).also { _toast.value = it }
        }
}