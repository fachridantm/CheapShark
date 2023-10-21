package com.technical.test.fachridan.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.technical.test.fachridan.core.domain.usecase.DealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val dealsUseCase: DealsUseCase
) : ViewModel() {
    fun getSearchDeals(title: String) = dealsUseCase.getSearchDeals(title).asLiveData()
}