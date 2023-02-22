package com.wrseven.movieapps.resources.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.wrseven.movieapps.resources.local.entity.MovieEntity
import com.wrseven.movieapps.resources.local.entity.TvEntity

@Dao
interface ItemDao {
    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovie(query: SimpleSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM table_movie WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM table_movie WHERE favorite = 1")
    fun getFavMovie(): DataSource.Factory<Int, MovieEntity>

    @RawQuery(observedEntities = [TvEntity::class])
    fun getTv(query: SimpleSQLiteQuery): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM table_tv WHERE id = :id")
    fun getTvById(id: Int): LiveData<TvEntity>

    @Query("SELECT * FROM table_tv WHERE favorite = 1")
    fun getFavTv(): DataSource.Factory<Int, TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tvs: List<TvEntity>)

    @Update
    fun updateTvShow(tv: TvEntity)
}