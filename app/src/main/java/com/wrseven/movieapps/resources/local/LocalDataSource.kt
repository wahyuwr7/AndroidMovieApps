package com.wrseven.movieapps.resources.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.wrseven.movieapps.resources.local.entity.MovieEntity
import com.wrseven.movieapps.resources.local.entity.TvEntity
import com.wrseven.movieapps.utils.SortUtils

class LocalDataSource(private val mItemDao: ItemDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: ItemDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getAllMovies(sort: String): DataSource.Factory<Int, MovieEntity> =
        mItemDao.getMovie(SortUtils.getSortedQuery(sort, "table_movie"))

    fun getMovieById(id: String): LiveData<MovieEntity> = mItemDao.getMovieById(id.toInt())

    fun getFavMovies(): DataSource.Factory<Int, MovieEntity> = mItemDao.getFavMovie()

    fun getAllTvShows(sort: String): DataSource.Factory<Int, TvEntity> =
        mItemDao.getTv(SortUtils.getSortedQuery(sort, "table_tv"))

    fun getTvShowById(id: String): LiveData<TvEntity> = mItemDao.getTvById(id.toInt())

    fun getFavTvShows(): DataSource.Factory<Int, TvEntity> = mItemDao.getFavTv()

    fun insertMovies(movies: List<MovieEntity>) = mItemDao.insertMovie(movies)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        mItemDao.updateMovie(movie)
    }

    fun getDetailMovie(movie: MovieEntity) {
        mItemDao.updateMovie(movie)
    }

    fun insertTvShows(tvShows: List<TvEntity>) = mItemDao.insertTv(tvShows)

    fun setFavoriteTvShow(tvShow: TvEntity, newState: Boolean) {
        tvShow.favorite = newState
        mItemDao.updateTvShow(tvShow)
    }

    fun getDetailTvShow(tvShow: TvEntity) {
        mItemDao.updateTvShow(tvShow)
    }
}