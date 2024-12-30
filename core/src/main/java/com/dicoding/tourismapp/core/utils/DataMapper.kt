package com.dicoding.tourismapp.core.utils

import android.util.Log
import com.dicoding.tourismapp.core.data.source.local.entity.FoodEntity
import com.dicoding.tourismapp.core.data.source.remote.response.DataItem
import com.dicoding.tourismapp.core.data.source.remote.response.FoodResponse
import com.dicoding.tourismapp.core.domain.model.Food

object DataMapper {
    fun mapResponsesToEntities(input: List<DataItem>): List<FoodEntity> {
        val tourismList = ArrayList<FoodEntity>()
        input.map {
            val tourism =
                FoodEntity(
                    fullPortrait = it.fullPortrait,
                    displayName = it.displayName,
                    description = it.description,
                    uuid = it.uuid,
                    developerName = it.developerName,
                    isFavorite = false
                )
            tourismList.add(tourism)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<FoodEntity>): List<Food> =
        input.map {
            Log.d("DataMapper", "Mapping agent with fullPortrait: ${it.fullPortrait}")
            Food(
                fullPortrait = it.fullPortrait ?: "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Valorant_logo_-_pink_color_version.svg/1280px-Valorant_logo_-_pink_color_version.svg.png",
                displayName = it.displayName,
                description = it.description,
                uuid = it.uuid,
                developerName = it.developerName,
                isFavorite = it.isFavorite,
            )
        }

    fun mapDomainToEntity(input: Food) =
        FoodEntity(
            fullPortrait = input.fullPortrait,
            displayName = input.displayName,
            description = input.description,
            uuid = input.uuid,
            developerName = input.developerName,
            isFavorite = input.isFavorite
        )
}