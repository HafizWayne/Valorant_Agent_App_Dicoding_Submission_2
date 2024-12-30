package com.dicoding.tourismapp.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.tourismapp.core.domain.model.Food
import com.dicoding.tourismapp.core.domain.usecase.FoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class DetailViewModel  (private val foodUseCase: FoodUseCase) : ViewModel() {
    fun setFavoriteFood(food: Food, newStatus:Boolean) =
        foodUseCase.setFavoriteFood(food, newStatus)
}

