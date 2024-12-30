package com.dicoding.tourismapp.core.domain.repository

import com.dicoding.tourismapp.core.data.Resource
import com.dicoding.tourismapp.core.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface IFoodRepository {

    fun getAllFood(): Flow<Resource<List<Food>>>

    fun getFavoriteFood(): Flow<List<Food>>

    fun setFavoriteFood(food: Food, state: Boolean)
}
