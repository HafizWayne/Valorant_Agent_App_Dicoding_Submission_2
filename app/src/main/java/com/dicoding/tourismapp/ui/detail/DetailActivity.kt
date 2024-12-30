package com.dicoding.tourismapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.tourismapp.R
import com.dicoding.tourismapp.core.domain.model.Food
import com.dicoding.tourismapp.databinding.ActivityDetailTourismBinding
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTourismBinding

    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTourismBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val detailFood = intent.getParcelableExtra<Food>(EXTRA_DATA)
        showDetailFood(detailFood)
    }

    private fun showDetailFood(detailFood: Food?) {
        detailFood?.let {
            supportActionBar?.title = detailFood.displayName
            binding.contentDetailTourism.tvDetailDescription.text = detailFood.description
            Glide.with(this@DetailActivity)
                .load(detailFood.fullPortrait)
                .into(binding.ivDetailImage)

            var statusFavorite = detailFood.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteFood(detailFood, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
