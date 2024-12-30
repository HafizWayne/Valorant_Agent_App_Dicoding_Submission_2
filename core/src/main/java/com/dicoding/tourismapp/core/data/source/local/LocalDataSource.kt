package com.dicoding.tourismapp.core.data.source.local

import com.dicoding.tourismapp.core.data.source.local.entity.FoodEntity
import com.dicoding.tourismapp.core.data.source.local.room.FoodDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class LocalDataSource (private val foodDao: FoodDao) {

    fun getAllFood(): Flow<List<FoodEntity>> = foodDao.getAllFood()

    fun getFavoriteFood(): Flow<List<FoodEntity>> = foodDao.getFavoriteFood()

    suspend fun insertFood(foodList: List<FoodEntity>) = foodDao.insertFood(foodList)

    fun setFavoriteFood(food: FoodEntity, newState: Boolean) {
        food.isFavorite = newState
        foodDao.updateFavoriteFood(food)
    }
}