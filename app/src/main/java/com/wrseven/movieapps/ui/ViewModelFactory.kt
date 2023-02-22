package com.wrseven.movieapps.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wrseven.movieapps.resources.ListRepository
import com.wrseven.movieapps.resources.di.AppInject
import com.wrseven.movieapps.ui.detail.DetailViewModel
import com.wrseven.movieapps.ui.favorite.movie.FavoriteMovieViewModel
import com.wrseven.movieapps.ui.favorite.tv.FavoriteTvViewModel
import com.wrseven.movieapps.ui.main.movie.MovieViewModel
import com.wrseven.movieapps.ui.main.tv.TvShowViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val listRepository: ListRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(classModel: Class<T>): T {
        return when {
            classModel.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(listRepository) as T
            }
            classModel.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(listRepository) as T
            }
            classModel.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(listRepository) as T
            }
            classModel.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(listRepository) as T
            }
            classModel.isAssignableFrom(FavoriteTvViewModel::class.java) -> {
                FavoriteTvViewModel(listRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + classModel.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(AppInject.repositoryProvider(context))
            }
    }
}