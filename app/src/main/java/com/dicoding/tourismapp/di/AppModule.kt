package com.dicoding.tourismapp.di

import com.dicoding.tourismapp.core.domain.usecase.FoodInteractor
import com.dicoding.tourismapp.core.domain.usecase.FoodUseCase
import com.dicoding.tourismapp.ui.detail.DetailViewModel
import com.dicoding.tourismapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FoodUseCase> { FoodInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}


