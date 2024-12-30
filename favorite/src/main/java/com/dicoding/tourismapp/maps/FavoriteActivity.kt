package com.dicoding.tourismapp.maps

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.tourismapp.core.data.Resource
import com.dicoding.tourismapp.core.ui.FoodAdapter
import com.dicoding.tourismapp.maps.databinding.ActivityFavoriteBinding
import com.dicoding.tourismapp.ui.detail.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    private val favoriteViewModel: FavoriteViewModel by viewModel()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Agent Favorite"

        val foodAdapter = FoodAdapter()
        foodAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.tourism.observe(this@FavoriteActivity) { dataMenu ->
            foodAdapter.submitList(dataMenu)
            binding.viewEmpty.root.visibility =
                if (dataMenu.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvMenu) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = foodAdapter
        }
    }


}