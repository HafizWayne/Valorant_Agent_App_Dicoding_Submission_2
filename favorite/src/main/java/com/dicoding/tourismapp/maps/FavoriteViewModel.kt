package com.dicoding.tourismapp.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.tourismapp.core.domain.usecase.FoodUseCase

class FavoriteViewModel(foodUseCase: FoodUseCase) : ViewModel() {
    val tourism = foodUseCase.getFavoriteTourism().asLiveData()
}