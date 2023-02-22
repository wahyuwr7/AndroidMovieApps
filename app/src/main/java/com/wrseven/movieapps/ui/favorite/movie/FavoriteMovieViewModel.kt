package com.wrseven.movieapps.ui.favorite.movie

import androidx.lifecycle.ViewModel
import com.wrseven.movieapps.resources.ListRepository
import com.wrseven.movieapps.resources.local.entity.MovieEntity

class FavoriteMovieViewModel(private val repository: ListRepository) : ViewModel() {
    fun getFavMovies() = repository.getFavoriteMovies()

    fun setFavMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.favorite
        repository.setFavoriteMovie(movieEntity, newState)
    }
}