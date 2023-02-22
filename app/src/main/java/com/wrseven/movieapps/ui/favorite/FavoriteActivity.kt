package com.wrseven.movieapps.ui.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wrseven.movieapps.R
import com.wrseven.movieapps.databinding.ActivityFavoriteBinding

@SuppressLint("SetTextI18n")
class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val stateItem: String = "ItemPosition"
    private var mPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupLayout()

        savedInstanceState?.putInt(stateItem, mPosition)
    }

    private fun setupLayout() {
        val favoriteSectionPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        binding.apply {
            viewPager.adapter = favoriteSectionPagerAdapter
            tabLayout.setupWithViewPager(binding.viewPager)
        }
        supportActionBar?.title = resources.getString(R.string.favorite)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mPosition = savedInstanceState.getInt(stateItem)
    }
}