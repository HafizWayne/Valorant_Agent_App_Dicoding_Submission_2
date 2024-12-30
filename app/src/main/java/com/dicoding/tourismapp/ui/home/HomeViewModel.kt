package com.dicoding.tourismapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.tourismapp.core.domain.usecase.FoodUseCase

class HomeViewModel (foodUseCase: FoodUseCase) : ViewModel() {
    val food = foodUseCase.getAllFood().asLiveData()
}

