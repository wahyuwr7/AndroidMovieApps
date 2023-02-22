package com.wrseven.movieapps.ui.main.movie

import androidx.lifecycle.ViewModel
import com.wrseven.movieapps.resources.ListRepository

class MovieViewModel(private val listRepository: ListRepository) : ViewModel() {
    fun getMovies(sort: String) = listRepository.getMovies(sort)
}