package com.ewide.test.fachridan.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ewide.test.fachridan.core.domain.usecase.DealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dealsUseCase: DealsUseCase) : ViewModel() {
    fun getListOfDeals() = dealsUseCase.getListOfDeals().asLiveData()
    fun getSortListOfDeals(sortBy: String) = dealsUseCase.getSortListOfDeals(sortBy).asLiveData()
}