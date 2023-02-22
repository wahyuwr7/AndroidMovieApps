package com.wrseven.movieapps.resources

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.wrseven.movieapps.resources.local.entity.MovieEntity
import com.wrseven.movieapps.resources.local.entity.TvEntity
import com.wrseven.movieapps.vo.Resource

interface ListDataSource {
    fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>
    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>>
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>
    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)
    fun getTvShows(sort: String): LiveData<Resource<PagedList<TvEntity>>>
    fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvEntity>>
    fun getFavoriteTvShows(): LiveData<PagedList<TvEntity>>
    fun setFavoriteTvShow(tvShow: TvEntity, state: Boolean)
}