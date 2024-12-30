package com.dicoding.tourismapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val fullPortrait: String,
    val displayName: String,
    val description: String,
    val uuid: String,
    val developerName: String,
    val isFavorite: Boolean
) : Parcelable