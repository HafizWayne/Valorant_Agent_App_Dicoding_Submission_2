package com.dicoding.tourismapp.core.data

import com.dicoding.tourismapp.core.data.source.local.LocalDataSource
import com.dicoding.tourismapp.core.data.source.remote.RemoteDataSource
import com.dicoding.tourismapp.core.data.source.remote.network.ApiResponse
import com.dicoding.tourismapp.core.data.source.remote.response.DataItem
import com.dicoding.tourismapp.core.data.source.remote.response.FoodResponse
import com.dicoding.tourismapp.core.domain.model.Food
import com.dicoding.tourismapp.core.domain.repository.IFoodRepository
import com.dicoding.tourismapp.core.utils.AppExecutors
import com.dicoding.tourismapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FoodRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IFoodRepository {

    override fun getAllFood(): Flow<Resource<List<Food>>> =
        object : NetworkBoundResource<List<Food>, List<DataItem>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Food>> {
                return localDataSource.getAllFood().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Food>?): Boolean =
                data.isNullOrEmpty() // mengambil data dari internet hanya jika data di database kosong
//                 true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<DataItem>>> =
                remoteDataSource.getAllFood()

            override suspend fun saveCallResult(data: List<DataItem>) {
                val foodList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertFood(foodList)
            }
        }.asFlow()

    override fun getFavoriteFood(): Flow<List<Food>> {
        return localDataSource.getFavoriteFood().map {
           DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteFood(food: Food, state: Boolean) {
        val foodEntity = DataMapper.mapDomainToEntity(food)
        appExecutors.diskIO().execute { localDataSource.setFavoriteFood(foodEntity, state) }
    }
}

