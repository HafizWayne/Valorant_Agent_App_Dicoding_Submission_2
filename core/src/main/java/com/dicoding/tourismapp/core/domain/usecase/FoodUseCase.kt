package com.dicoding.tourismapp.core.domain.usecase

import com.dicoding.tourismapp.core.data.Resource
import com.dicoding.tourismapp.core.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodUseCase {
    fun getAllFood(): Flow<Resource<List<Food>>>
    fun getFavoriteTourism(): Flow<List<Food>>
    fun setFavoriteFood(food: Food, state: Boolean)
}