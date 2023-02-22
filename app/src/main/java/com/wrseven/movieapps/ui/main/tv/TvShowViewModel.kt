package com.wrseven.movieapps.ui.main.tv

import androidx.lifecycle.ViewModel
import com.wrseven.movieapps.resources.ListRepository

class TvShowViewModel(private val movieCatalogueRepository: ListRepository) : ViewModel() {
    fun getTvShows(sort: String) = movieCatalogueRepository.getTvShows(sort)
}