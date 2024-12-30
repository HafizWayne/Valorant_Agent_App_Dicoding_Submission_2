package com.dicoding.tourismapp.core.domain.usecase

import com.dicoding.tourismapp.core.data.Resource
import com.dicoding.tourismapp.core.domain.model.Food
import com.dicoding.tourismapp.core.domain.repository.IFoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodInteractor  (private val tourismRepository: IFoodRepository): FoodUseCase {

    override fun getAllFood(): Flow<Resource<List<Food>>> = tourismRepository.getAllFood()

    override fun getFavoriteTourism(): Flow<List<Food>> = tourismRepository.getFavoriteFood()

    override fun setFavoriteFood(food: Food, state: Boolean) = tourismRepository.setFavoriteFood(food, state)
}