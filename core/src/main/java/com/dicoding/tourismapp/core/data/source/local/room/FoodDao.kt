package com.dicoding.tourismapp.core.data.source.local.room

import androidx.room.*
import com.dicoding.tourismapp.core.data.source.local.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("SELECT * FROM agent")
    fun getAllFood(): Flow<List<FoodEntity>>

    @Query("SELECT * FROM agent where isFavorite = 1")
    fun getFavoriteFood(): Flow<List<FoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(menu: List<FoodEntity>)

    @Update
    fun updateFavoriteFood(menu: FoodEntity)
}
