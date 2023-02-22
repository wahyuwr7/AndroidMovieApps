package com.wrseven.movieapps.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.wrseven.movieapps.R
import com.wrseven.movieapps.databinding.ActivityDetailBinding
import com.wrseven.movieapps.resources.local.entity.MovieEntity
import com.wrseven.movieapps.resources.local.entity.TvEntity
import com.wrseven.movieapps.ui.ViewModelFactory
import com.wrseven.movieapps.utils.Construct.BASE_IMG_URL
import com.wrseven.movieapps.utils.Construct.MV_TITLE
import com.wrseven.movieapps.utils.Construct.TV_TITLE
import com.wrseven.movieapps.utils.Construct.setGlideImage
import com.wrseven.movieapps.vo.Status.*

@SuppressLint("SetTextI18n")
class DetailActivity : AppCompatActivity(),
    View.OnClickListener {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showProgressBar(true)
        showItem()
    }

    private fun showItem() {
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        binding.btnFavorite.setOnClickListener(this)

        val extras = intent.extras
        if (extras != null) {
            val dataId = extras.getString(EXTRA_ITEM)
            category = extras.getString(EXTRA_TYPE)

            if (dataId != null && category != null) {
                viewModel.setItem(dataId.toInt(), category.toString())
                setupState()
                if (category == MV_TITLE) {
                    setTitle(MV_TITLE)
                    viewModel.getDetailMovie().observe(this, { detail ->
                        when (detail.status) {
                            LOADING -> showProgressBar(true)
                            SUCCESS -> {
                                if (detail.data != null) {
                                    showProgressBar(false)
                                    showMovieDetail(detail.data)
                                }
                            }
                            ERROR -> {
                                showProgressBar(false)
                                Toast.makeText(
                                    applicationContext,
                                    "Terjadi kesalahan",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
                } else if (category == TV_TITLE) {
                    setTitle(TV_TITLE)
                    viewModel.getDetailTvShow().observe(this, { detail ->
                        when (detail.status) {
                            LOADING -> showProgressBar(true)
                            SUCCESS -> {
                                if (detail.data != null) {
                                    showProgressBar(false)
                                    showTvDetail(detail.data)
                                }
                            }
                            ERROR -> {
                                showProgressBar(false)
                                Toast.makeText(
                                    applicationContext,
                                    "Terjadi kesalahan",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_favorite -> {
                onBtnFavClicked()
            }
        }
    }

    private fun showMovieDetail(movie: MovieEntity) {
        binding.apply {
            val year = movie.year.subSequence(0, 4)
            tvDetailTitle.text = movie.title + " (${year})"
            tvDetailDesc.text = movie.overview
            tvDetailRating.text = movie.rating.toString().subSequence(0, 3)

            setGlideImage(
                this@DetailActivity,
                BASE_IMG_URL + movie.poster,
                ivDetailPoster,
                R.drawable.placeholder
            )

            setGlideImage(
                this@DetailActivity,
                BASE_IMG_URL + movie.backdrop,
                ivDetailPreview,
                R.drawable.placeholder
            )

            showProgressBar(false)
        }
    }

    private fun showTvDetail(tv: TvEntity) {
        binding.apply {
            val year = tv.year.subSequence(0, 4)
            tvDetailTitle.text = tv.name + " (${year})"
            tvDetailDesc.text = tv.overview
            tvDetailRating.text = tv.rating.toString().subSequence(0, 3)

            setGlideImage(
                this@DetailActivity,
                BASE_IMG_URL + tv.poster,
                ivDetailPoster,
                R.drawable.placeholder
            )

            setGlideImage(
                this@DetailActivity,
                BASE_IMG_URL + tv.backdrop,
                ivDetailPreview,
                R.drawable.placeholder
            )

            showProgressBar(false)
        }
    }

    private fun setupState() {
        if (category == MV_TITLE) {
            viewModel.getDetailMovie().observe(this, { movie ->
                when (movie.status) {
                    LOADING -> showProgressBar(true)
                    SUCCESS -> {
                        if (movie.data != null) {
                            showProgressBar(false)
                            val state = movie.data.favorite
                            setFavState(state)
                        }
                    }
                    ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        } else if (category == TV_TITLE) {
            viewModel.getDetailTvShow().observe(this, { tvShow ->
                when (tvShow.status) {
                    LOADING -> showProgressBar(true)
                    SUCCESS -> {
                        if (tvShow.data != null) {
                            showProgressBar(false)
                            val state = tvShow.data.favorite
                            setFavState(state)
                        }
                    }
                    ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        }
    }

    private fun onBtnFavClicked() {
        if (category == TV_TITLE) {
            viewModel.setFavoriteTvShow()
        } else {
            viewModel.setFavoriteMovie()
        }
    }

    private fun setFavState(state: Boolean) {
        binding.apply {
            if (state) {
                btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
    }

    private fun showProgressBar(state: Boolean) {
        binding.apply {
            progressCircular.isVisible = state
            btnFavorite.isInvisible = state
        }
    }

    private fun setTitle(type: String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = type
    }

    companion object {
        const val EXTRA_ITEM = "extra_item"
        const val EXTRA_TYPE = "extra_type"
    }
}