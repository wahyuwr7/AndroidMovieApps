package com.wrseven.movieapps.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wrseven.movieapps.resources.ListRepository
import com.wrseven.movieapps.resources.local.entity.MovieEntity
import com.wrseven.movieapps.resources.local.entity.TvEntity
import com.wrseven.movieapps.utils.Construct.MV_TITLE
import com.wrseven.movieapps.utils.Construct.TV_TITLE
import com.wrseven.movieapps.vo.Resource

class DetailViewModel(private val listRepository: ListRepository) : ViewModel() {
    private lateinit var detailTv: LiveData<Resource<TvEntity>>
    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>

    fun setItem(id: Int, category: String) {
        when (category) {
            TV_TITLE -> {
                detailTv = listRepository.getDetailTvShow(id)
            }

            MV_TITLE -> {
                detailMovie = listRepository.getDetailMovie(id)
            }
        }
    }

    fun setFavoriteMovie() {
        val resource = detailMovie.value
        if (resource?.data != null) {
            val newState = !resource.data.favorite
            listRepository.setFavoriteMovie(resource.data, newState)
        }
    }

    fun setFavoriteTvShow() {
        val resource = detailTv.value
        if (resource?.data != null) {
            val newState = !resource.data.favorite
            listRepository.setFavoriteTvShow(resource.data, newState)
        }
    }

    fun getDetailTvShow() = detailTv
    fun getDetailMovie() = detailMovie
}