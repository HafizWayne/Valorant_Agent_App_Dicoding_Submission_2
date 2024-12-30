package com.dicoding.tourismapp.core.data.source.remote.network

import com.dicoding.tourismapp.core.data.source.remote.response.FoodResponse
import retrofit2.http.GET

interface ApiService {
    @GET("agents")
    suspend fun getList(): FoodResponse
}
