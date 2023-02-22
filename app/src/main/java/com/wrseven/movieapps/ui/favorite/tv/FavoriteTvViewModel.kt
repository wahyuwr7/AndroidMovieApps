package com.wrseven.movieapps.ui.favorite.tv

import androidx.lifecycle.ViewModel
import com.wrseven.movieapps.resources.ListRepository
import com.wrseven.movieapps.resources.local.entity.TvEntity

class FavoriteTvViewModel(private val repository: ListRepository) : ViewModel() {
    fun getFavTvShows() = repository.getFavoriteTvShows()

    fun setFavTvShow(tvEntity: TvEntity) {
        val newState = !tvEntity.favorite
        repository.setFavoriteTvShow(tvEntity, newState)
    }
}